package com.luizjacomn.patmanapi.patient.service;

import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.patient.repository.PatientRepository;
import com.luizjacomn.patmanapi.patient.repository.filter.PatientFilter;
import com.luizjacomn.patmanapi.patient.repository.spec.PatientSpecs;
import com.luizjacomn.patmanapi.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    /**
     * Método responsável por editar pacientes.
     * @param id do paciente a ser buscado
     * @param patient dados de paciente que serão salvos
     * @throws {@link ResourceNotFoundException} caso não encontre
     */
    @Transactional
    public void editar(UUID id, Patient patient) {
        this.buscarPorId(id);

        patient.setId(id);

        patientRepository.save(patient);
    }

    /**
     * Método responsável por listar os pacientes cadastrados (pode filtrar dados)
     * @param patientFilter filtro para os dados (opcionais)
     * @return {@link List}<{@link Patient}> com os dados retornados
     */
    public List<Patient> listar(PatientFilter patientFilter) {
        return patientRepository.findAll(PatientSpecs.filtering(patientFilter));
    }

    /**
     * Método responsável por buscar um paciente pelo seu id (UUID)
     * @param id do paciente a ser buscado
     * @return {@link Patient} buscado
     * @throws {@link ResourceNotFoundException} caso não encontre
     */
    public Patient buscarPorId(UUID id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("id = " + id));
    }

    /**
     * Método responsável por deletar uma paciente pelo seu id (UUID)
     * @param id do paciente a ser deletado
     * @throws {@link ResourceNotFoundException} caso não encontre
     */
    @Transactional
    public void deletar(UUID id) {
        this.buscarPorId(id);

        patientRepository.deleteById(id);
    }

}
