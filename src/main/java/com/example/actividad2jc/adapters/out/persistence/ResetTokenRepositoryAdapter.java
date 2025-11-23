package com.example.actividad2jc.adapters.out.persistence;

import org.springframework.stereotype.Component;
import com.example.actividad2jc.adapters.out.persistence.repository.ResetTokenJpaRepository;
import com.example.actividad2jc.adapters.out.persistence.ResetTokenEntity;

import java.time.Instant;
import java.util.Optional;

@Component
public class ResetTokenRepositoryAdapter {

    private final ResetTokenJpaRepository jpa;

    public ResetTokenRepositoryAdapter(ResetTokenJpaRepository jpa) {
        this.jpa = jpa;
    }

    public void save(ResetTokenEntity entity) {
        jpa.save(entity);
    }

    public Optional<ResetTokenEntity> findByToken(String token) {
        return jpa.findById(token);
    }

    public void deleteByToken(String token) {
        jpa.deleteById(token);
    }
}
