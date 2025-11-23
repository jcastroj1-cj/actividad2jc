package com.example.actividad2jc.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.actividad2jc.domain.port.out.UserRepositoryPort;
import com.example.actividad2jc.domain.model.User;

@Service
public class RegisterUserUseCase {
    private final UserRepositoryPort userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepositoryPort userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String email, String password) {
        var hashed = passwordEncoder.encode(password);
        var user = new User(null, email, hashed, true);
        return userRepo.save(user);
    }
}
