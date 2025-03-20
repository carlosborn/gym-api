package com.gym.infra.controllers;

import com.gym.application.dtos.LoginIDTO;
import com.gym.application.dtos.LoginODTO;
import com.gym.application.security.jwt.JwtService;
import com.gym.application.services.UserSessionApplicationService;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionApplicationService userSessionApplicationService;

    @PostMapping("/login")
    public ResponseEntity<LoginODTO> login(@RequestBody LoginIDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.username());
        String token = jwtService.generateToken(userDetails);

        Optional<UserEntity> userEntity = userService.findByUsername(loginRequest.username());
        userEntity.ifPresent(user -> userSessionApplicationService.createUserSession(user));


        return ResponseEntity.ok(new LoginODTO(token));
    }
}
