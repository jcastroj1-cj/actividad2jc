package com.example.actividad2jc.domain.port.out;

import com.example.actividad2jc.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}
