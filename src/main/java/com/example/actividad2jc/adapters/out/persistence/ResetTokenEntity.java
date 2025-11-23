package com.example.actividad2jc.adapters.out.persistence;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reset_tokens")
public class ResetTokenEntity {
    @Id
    private String token;

    @Column(nullable = false)
    private java.util.UUID userId;

    @Column(nullable = false)
    private Instant expiresAt;

    public ResetTokenEntity() {}

    public ResetTokenEntity(String token, java.util.UUID userId, Instant expiresAt) {
        this.token = token;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public java.util.UUID getUserId() { return userId; }
    public void setUserId(java.util.UUID userId) { this.userId = userId; }

    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
}
