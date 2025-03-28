package com.gym.application.services;

import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.PaymentEntity;
import com.gym.domain.entities.PaymentStatus;
import com.gym.domain.exceptions.PaymentNotCreatedException;
import com.gym.domain.exceptions.PaymentNotFoundException;
import com.gym.domain.exceptions.PaymentNotPaidException;
import com.gym.domain.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentApplicationService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EnrollmentApplicationService enrollmentApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    public void createPayments(Long enrollmentId, Date firstDueDate) {
        EnrollmentEntity enrollmentEntity = this.enrollmentApplicationService.getById(enrollmentId);

        Integer duration = enrollmentEntity.getMembershipPlan().getDuration();
        int enrollmentNumber = 1; // Controla a quantidade de mensalidades geradas
        Double value = enrollmentEntity.getMembershipPlan().getMonthlyFee();
        Date dueDate = firstDueDate;

        while (enrollmentNumber <= duration) {
            PaymentEntity paymentEntity = this.createPayment(enrollmentEntity, value, dueDate);
            if (paymentEntity.getId() == null) {
                throw new PaymentNotCreatedException();
            }

            // Adiciona 1 mes sobre a data de vencimento
            LocalDate localDate = LocalDate.ofEpochDay(dueDate.getTime());
            dueDate = new Date(localDate.plusMonths(1).toEpochDay());

            enrollmentNumber++;
        }
    }

    private PaymentEntity createPayment(EnrollmentEntity enrollmentEntity, Double value, Date dueDate) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setEnrollment(enrollmentEntity);
        paymentEntity.setStatus(PaymentStatus.PENDING);
        paymentEntity.setValue(value);
        paymentEntity.setDueDate(dueDate);
        paymentEntity.setCreatedAt(new Date());
        paymentEntity.setUpdatedAt(new Date());

        return this.paymentService.save(paymentEntity);
    }

    public PaymentEntity getById(Long id) {
        Optional<PaymentEntity> optional = this.paymentService.findById(id);
        if (optional.isEmpty()) {
            throw new PaymentNotFoundException();
        }
        return optional.get();
    }

    public boolean pay(Long id, Date paidAt) {
        PaymentEntity paymentEntity = this.getById(id);
        return this.pay(paymentEntity, paidAt);
    }

    public boolean pay(PaymentEntity paymentEntity, Date paidAt) {
        paymentEntity.setStatus(PaymentStatus.PAID);
        paymentEntity.setPaidAt(paidAt);
        paymentEntity.setUpdatedAt(new Date());

        return this.paymentService.save(paymentEntity).getPaidAt() != null;
    }

    public boolean cancel(Long id) {
        PaymentEntity paymentEntity = this.getById(id);
        return this.cancel(paymentEntity);
    }

    public boolean cancel(PaymentEntity paymentEntity) {
        paymentEntity.setStatus(PaymentStatus.CANCELLED);
        paymentEntity.setUpdatedAt(new Date());

        return this.paymentService.save(paymentEntity).getStatus() == PaymentStatus.CANCELLED;
    }

    public boolean payAllByEnrollment(Long enrollmentId, Date paidDate) {
        List<PaymentEntity> payments = this.getByEnrollment(enrollmentId);
        for (PaymentEntity paymentEntity : payments) {
            if (!this.pay(paymentEntity, paidDate)) {
                throw new PaymentNotPaidException();
            }
        }
        return true;
    }

    public boolean cancelAllByEnrollment(Long enrollmentId, Date paidDate) {
        List<PaymentEntity> payments = this.getByEnrollment(enrollmentId);
        for (PaymentEntity paymentEntity : payments) {
            if (!this.cancel(paymentEntity)) {
                throw new PaymentNotPaidException();
            }
        }
        return true;
    }

    public List<PaymentEntity> getByEnrollment(Long enrollmentId) {
        EnrollmentEntity enrollmentEntity = this.enrollmentApplicationService.getById(enrollmentId);
        return this.paymentService.findByEnrollment(enrollmentEntity);
    }

}
