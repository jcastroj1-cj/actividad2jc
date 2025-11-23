package com.example.actividad2jc.adapters.out.persistence;

import org.springframework.stereotype.Component;
import com.example.actividad2jc.domain.model.User;
import com.example.actividad2jc.domain.port.out.UserRepositoryPort;
import com.example.actividad2jc.adapters.out.persistence.repository.UserJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpa;

    public UserRepositoryAdapter(UserJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        var entity = UserJpaEntity.fromDomain(user);
        var saved = jpa.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpa.findByEmail(email).map(UserJpaEntity::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpa.findById(id).map(UserJpaEntity::toDomain);
    }
}
