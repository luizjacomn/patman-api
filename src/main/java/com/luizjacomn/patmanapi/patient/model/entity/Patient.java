package com.luizjacomn.patmanapi.patient.model.entity;

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
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
    name = "patients",
    indexes = {
        @Index(name = "idx_patients_name", columnList = "name"),
        @Index(name = "idx_patients_cpf", columnList = "cpf", unique = true)
    }
)
public class Patient implements Serializable {

    @Serial
    private static final long serialVersionUID = -1936944103589665001L;

    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @EqualsAndHashCode.Include
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @EqualsAndHashCode.Include
    @Column(columnDefinition = "TEXT")
    private String email;

    @EqualsAndHashCode.Include
    @Column(columnDefinition = "TEXT")
    private String phone;

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
