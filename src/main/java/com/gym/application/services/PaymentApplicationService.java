package com.gym.application.services;

import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.PaymentEntity;
import com.gym.domain.entities.PaymentStatus;
import com.gym.domain.exceptions.PaymentAlreadyPaidException;
import com.gym.domain.exceptions.PaymentNotCreatedException;
import com.gym.domain.exceptions.PaymentNotFoundException;
import com.gym.domain.exceptions.PaymentNotPaidException;
import com.gym.domain.services.PaymentService;
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

    public void createPayments(EnrollmentEntity enrollmentEntity, LocalDate firstDueDate) {
        Integer duration = enrollmentEntity.getMembershipPlan().getDuration();
        int enrollmentNumber = 1; // Controla a quantidade de mensalidades geradas
        Double value = enrollmentEntity.getMembershipPlan().getMonthlyFee();
        LocalDate dueDate = firstDueDate;

        while (enrollmentNumber <= duration) {
            PaymentEntity paymentEntity = this.createPayment(enrollmentEntity, value, dueDate);
            if (paymentEntity.getId() == null) {
                throw new PaymentNotCreatedException();
            }

            // Adiciona 1 mes sobre a data de vencimento
            dueDate = dueDate.plusMonths(1);

            enrollmentNumber++;
        }
    }

    private PaymentEntity createPayment(EnrollmentEntity enrollmentEntity, Double value, LocalDate dueDate) {
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

    public void pay(Long id, Date paidAt) {
        PaymentEntity paymentEntity = this.getById(id);
        this.pay(paymentEntity, paidAt);
    }

    public void pay(PaymentEntity paymentEntity, Date paidAt) {

        if (paymentEntity.getStatus() == PaymentStatus.PAID) {
            throw new PaymentAlreadyPaidException();
        }

        paymentEntity.setStatus(PaymentStatus.PAID);
        paymentEntity.setPaidAt(paidAt);
        paymentEntity.setUpdatedAt(new Date());

        if (this.paymentService.save(paymentEntity).getPaidAt() == null) {
            throw new PaymentNotPaidException();
        }
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

    public boolean payAllByEnrollment(EnrollmentEntity enrollmentEntity, Date paidDate) {
        List<PaymentEntity> payments = this.getByEnrollment(enrollmentEntity);
        for (PaymentEntity paymentEntity : payments) {
            this.pay(paymentEntity, paidDate);
        }
        return true;
    }

    public boolean cancelAllByEnrollment(EnrollmentEntity enrollmentEntity) {
        List<PaymentEntity> payments = this.getByEnrollment(enrollmentEntity);
        for (PaymentEntity paymentEntity : payments) {
            if (!this.cancel(paymentEntity)) {
                throw new PaymentNotPaidException();
            }
        }
        return true;
    }

    public List<PaymentEntity> getByEnrollment(EnrollmentEntity enrollmentEntity) {
        return this.paymentService.findByEnrollment(enrollmentEntity);
    }

}
