package com.example.actividad2jc.adapters.in.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;

import com.example.actividad2jc.application.usecase.RegisterUserUseCase;
import com.example.actividad2jc.application.usecase.ResetPasswordUseCase;
import com.example.actividad2jc.application.dto.RegisterRequest;
import com.example.actividad2jc.application.dto.LoginRequest;
import com.example.actividad2jc.application.dto.ResetRequest;
import com.example.actividad2jc.application.dto.ResetConfirmRequest;
import com.example.actividad2jc.domain.port.out.UserRepositoryPort;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase register;
    private final ResetPasswordUseCase reset;
    private final UserRepositoryPort userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(RegisterUserUseCase register,
                          ResetPasswordUseCase reset,
                          UserRepositoryPort userRepo,
                          PasswordEncoder passwordEncoder) {
        this.register = register;
        this.reset = reset;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        var user = register.register(req.email(), req.password());
        return ResponseEntity.ok(Map.of("id", user.getId(), "email", user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var userOpt = userRepo.findByEmail(req.email());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error","invalid")); 
        var user = userOpt.get();
        if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body(Map.of("error","invalid")); 
        }
        return ResponseEntity.ok(Map.of("message","ok"));
    }

    @PostMapping("/reset-request")
    public ResponseEntity<?> requestReset(@RequestBody ResetRequest req) {
        reset.requestReset(req.email());
        return ResponseEntity.ok(Map.of("message","Si el email existe, se ha enviado un link")); 
    }

    @PostMapping("/reset-confirm")
    public ResponseEntity<?> confirmReset(@RequestBody ResetConfirmRequest req) {
        try {
            reset.confirmReset(req.token(), req.newPassword(), passwordEncoder);
            return ResponseEntity.ok(Map.of("message","Contraseña cambiada")); 
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }
}
