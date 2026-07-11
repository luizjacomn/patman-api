package com.luizjacomn.patmanapi.patient.controller.v1.mapper;

import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientResponse;
import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.shared.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PatientResponseMapper implements Mapper<Patient, PatientResponse> {

    @Override
    public PatientResponse to(Patient input) {
        return new PatientResponse(input.getName(), input.getCpf());
    }

}
