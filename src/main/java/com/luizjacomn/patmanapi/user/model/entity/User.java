package com.luizjacomn.patmanapi.user.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_users_username", columnList = "username")
    }
)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 2673517626968582048L;

    @Id
    @GeneratedValue
    private UUID id;

    @EqualsAndHashCode.Include
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", updatable = false)
    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }

}
