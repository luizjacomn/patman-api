package com.luizjacomn.patmanapi.patient.service;

import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.patient.repository.PatientRepository;
import com.luizjacomn.patmanapi.patient.repository.filter.PatientFilter;
import com.luizjacomn.patmanapi.patient.repository.spec.PatientSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * Método responsável por cadastrar pacientes.
     * @param patient dados de paciente que serão salvos
     * @return {@link Patient salvo}
     */
    @Transactional
    public Patient salvar(Patient patient) {
        // Regras de negócio aqui

        return patientRepository.save(patient);
    }

    public List<Patient> list(PatientFilter patientFilter) {
        return patientRepository.findAll(PatientSpecs.filtering(patientFilter));
    }

}
