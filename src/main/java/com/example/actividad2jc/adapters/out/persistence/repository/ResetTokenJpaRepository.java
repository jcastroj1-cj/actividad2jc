package com.example.actividad2jc.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.actividad2jc.adapters.out.persistence.ResetTokenEntity;

public interface ResetTokenJpaRepository extends JpaRepository<ResetTokenEntity, String> {
}
