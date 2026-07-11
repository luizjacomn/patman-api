package com.luizjacomn.patmanapi.patient.controller.v1.dto;

import com.luizjacomn.patmanapi.shared.constant.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PatientRequest(
    @NotBlank
    String name,

    @NotBlank
    @CPF
    String cpf,

    @NotNull
    @PastOrPresent
    LocalDate birthDate,

    @Email
    String email,

    @Pattern(regexp = ValidationConstants.PHONE_REGEX, message = "{patient.phone.pattern}")
    String phone
) { }
