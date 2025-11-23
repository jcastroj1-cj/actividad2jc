package com.example.actividad2jc.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import com.example.actividad2jc.domain.port.out.UserRepositoryPort;
import com.example.actividad2jc.domain.port.out.EmailSenderPort;
import com.example.actividad2jc.adapters.out.persistence.ResetTokenEntity;
import com.example.actividad2jc.adapters.out.persistence.ResetTokenRepositoryAdapter;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordUseCase {
    private final UserRepositoryPort userRepo;
    private final ResetTokenRepositoryAdapter tokenRepo;
    private final EmailSenderPort emailSender;
    private final long tokenExpirySeconds;

    public ResetPasswordUseCase(UserRepositoryPort userRepo,
                                ResetTokenRepositoryAdapter tokenRepo,
                                EmailSenderPort emailSender,
                                @Value("${app.reset.token.expiration-seconds:3600}") long tokenExpirySeconds) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.emailSender = emailSender;
        this.tokenExpirySeconds = tokenExpirySeconds;
    }

    public void requestReset(String email) {
        var userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) return; // do not reveal existence
        var user = userOpt.get();
        var token = UUID.randomUUID().toString();
        var expiresAt = Instant.now().plusSeconds(tokenExpirySeconds);
        var entity = new ResetTokenEntity(token, user.getId(), expiresAt);
        tokenRepo.save(entity);
        var link = "http://localhost:8080/reset-form.html?token=" + token;
        var body = "Pincha el enlace para restablecer tu contraseña: " + link;
        emailSender.send(user.getEmail(), "Reset password", body);
    }

    public void confirmReset(String token, String newPassword, org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        Optional<ResetTokenEntity> tokenOpt = tokenRepo.findByToken(token);
        if (tokenOpt.isEmpty()) throw new IllegalArgumentException("Invalid token");
        var rt = tokenOpt.get();
        if (rt.getExpiresAt().isBefore(Instant.now())) {
            tokenRepo.deleteByToken(token);
            throw new IllegalArgumentException("Token expired");
        }
        var userOpt = userRepo.findById(rt.getUserId());
        if (userOpt.isEmpty()) throw new IllegalArgumentException("Invalid token");
        var user = userOpt.get();
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        tokenRepo.deleteByToken(token);
    }
}
