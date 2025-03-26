package com.gym.infra.controllers;

import com.gym.application.dtos.UserIDTO;
import com.gym.application.dtos.UserODTO;
import com.gym.application.dtos.UserSessionODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.UserApplicationService;
import com.gym.application.services.UserSessionApplicationService;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserSessionEntity;
import com.gym.domain.entities.UserStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserSessionApplicationService userSessionApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/actives")
    public ResponseEntity<DefaultPageModel<UserODTO>> getAllActiveUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10", name = "per_page") int perPage
    ) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<UserEntity> allActiveUsers = this.userApplicationService.getAllByStatus(UserStatus.ACTIVE, pageable);
        List<UserODTO> allActivesUsersODTO = getUserODTOS(allActiveUsers);

        DefaultPageModel<UserODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allActivesUsersODTO, pageable, allActivesUsersODTO.size()));

        return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserODTO> getById(@PathVariable Long id) {
        UserEntity userEntity = this.userApplicationService.getUserById(id);
        UserODTO userODTO = this.modelMapper.map(userEntity, UserODTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userODTO);
    }

    @GetMapping
    public ResponseEntity<DefaultPageModel<UserODTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10", name = "per_page") int perPage
    ) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<UserEntity> allUsers = this.userApplicationService.getAll(pageable);
        List<UserODTO> allUsersODTO = getUserODTOS(allUsers);

        DefaultPageModel<UserODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allUsersODTO, pageable, allUsersODTO.size()));

        return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
    }

    @PostMapping
    public ResponseEntity<String> createNewUser(@RequestBody @Validated UserIDTO userIDTO) {
        this.userApplicationService.createUser(userIDTO.name(), userIDTO.username(), userIDTO.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserIDTO userIDTO) {
        this.userApplicationService.updateUser(id, userIDTO.name(), userIDTO.password(), UserStatus.getEnum(userIDTO.status()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<DefaultPageModel<UserSessionODTO>> getAllSessionsByUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10", name = "per_page") int perPage
    ) {

        Pageable pageable = PageRequest.of(page, perPage);
        UserEntity userEntity = this.userApplicationService.getUserById(id);
        Page<UserSessionEntity> allSessionsUser = this.userSessionApplicationService.getByUser(userEntity, pageable);
        List<UserSessionODTO> allSessionsUsersODTO = getSessionsUserODTOs(allSessionsUser);

        DefaultPageModel<UserSessionODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allSessionsUsersODTO, pageable, allSessionsUsersODTO.size()));

        return ResponseEntity.status(HttpStatus.OK).body(pagedModel);
    }

    private List<UserODTO> getUserODTOS(Page<UserEntity> users) {
        List<UserODTO> usersODTO = new ArrayList<>();
        for (UserEntity userEntity : users) {
            usersODTO.add(this.modelMapper.map(userEntity, UserODTO.class));
        }
        return usersODTO;
    }

    private List<UserSessionODTO> getSessionsUserODTOs(Page<UserSessionEntity> allSessionsUser) {
        List<UserSessionODTO> usersSessionsODTO = new ArrayList<>();
        for (UserSessionEntity userSessionEntity : allSessionsUser) {

            // UserODTO userODTO = this.modelMapper.map(userSessionEntity.getUser(), UserODTO.class);

            usersSessionsODTO.add(this.modelMapper.map(userSessionEntity, UserSessionODTO.class));
        }
        return usersSessionsODTO;
    }
}
