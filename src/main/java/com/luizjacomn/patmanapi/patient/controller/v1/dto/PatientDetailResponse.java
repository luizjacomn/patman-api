package com.luizjacomn.patmanapi.patient.controller.v1.dto;

import java.time.LocalDate;

public record PatientDetailResponse(String name, String cpf, LocalDate birthDate, String email, String phone) { }
