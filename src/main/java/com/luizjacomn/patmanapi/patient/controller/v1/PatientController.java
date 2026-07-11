package com.luizjacomn.patmanapi.patient.controller.v1;

import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientDetailResponse;
import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientRequest;
import com.luizjacomn.patmanapi.patient.controller.v1.dto.PatientResponse;
import com.luizjacomn.patmanapi.patient.controller.v1.mapper.PatientDetailResponseMapper;
import com.luizjacomn.patmanapi.patient.repository.filter.PatientFilter;
import com.luizjacomn.patmanapi.patient.controller.v1.mapper.PatientRequestMapper;
import com.luizjacomn.patmanapi.patient.controller.v1.mapper.PatientResponseMapper;
import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    private final PatientRequestMapper patientRequestMapper;

    private final PatientResponseMapper patientResponseMapper;

    private final PatientDetailResponseMapper patientDetailResponseMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid PatientRequest request) {
        Patient pacienteSalvo = patientService.salvar(patientRequestMapper.to(request));

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pacienteSalvo.getId())
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void save(@PathVariable UUID id, @RequestBody @Valid PatientRequest request) {
        patientService.editar(id, patientRequestMapper.to(request));
    }

    @GetMapping
    public List<PatientResponse> list(PatientFilter patientFilter) {
        return patientResponseMapper.toList(patientService.listar(patientFilter));
    }

    @GetMapping("/{id}")
    public PatientDetailResponse find(@PathVariable UUID id) {
        return patientDetailResponseMapper.to(patientService.buscarPorId(id));
    }

}
