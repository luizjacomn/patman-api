package com.luizjacomn.patmanapi.patient.controller.v1.mapper;

import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientRequest;
import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.shared.mapper.Mapper;
import com.luizjacomn.patmanapi.shared.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PatientRequestMapper implements Mapper<PatientRequest, Patient> {

    @Override
    public Patient to(PatientRequest input) {
        Patient patient = new Patient();
        patient.setName(input.name());
        patient.setCpf(StringUtils.onlyNumbers(input.cpf()));
        patient.setEmail(input.email());
        patient.setPhone(input.phone());
        patient.setBirthDate(input.birthDate());

        return patient;
    }

}
