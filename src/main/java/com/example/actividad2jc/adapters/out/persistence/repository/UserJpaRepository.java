package com.example.actividad2jc.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import com.example.actividad2jc.adapters.out.persistence.UserJpaEntity;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmail(String email);
}
