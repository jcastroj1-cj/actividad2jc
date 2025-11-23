package com.example.actividad2jc.adapters.out.persistence;

import jakarta.persistence.*;
import java.util.UUID;
import com.example.actividad2jc.domain.model.User;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private boolean enabled;

    public UserJpaEntity() {}

    public UserJpaEntity(UUID id, String email, String passwordHash, boolean enabled) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.enabled = enabled;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public User toDomain() {
        return new User(this.id, this.email, this.passwordHash, this.enabled);
    }

    public static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(user.getId(), user.getEmail(), user.getPasswordHash(), user.isEnabled());
    }
}
