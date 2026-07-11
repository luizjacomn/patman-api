package com.luizjacomn.patmanapi.patient.controller.v1.mapper;

import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientDetailResponse;
import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.shared.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PatientDetailResponseMapper implements Mapper<Patient, PatientDetailResponse> {

    @Override
    public PatientDetailResponse to(Patient input) {
        return new PatientDetailResponse(
            input.getName(),
            input.getCpf(),
            input.getBirthDate(),
            input.getEmail(),
            input.getPhone()
        );
    }

}
