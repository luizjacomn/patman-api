package com.luizjacomn.patmanapi.patient.repository.spec;

import com.luizjacomn.patmanapi.patient.model.entity.Patient;
import com.luizjacomn.patmanapi.patient.repository.filter.PatientFilter;
import com.luizjacomn.patmanapi.shared.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatientSpecs {

    public static Specification<Patient> filtering(final PatientFilter patientFilter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (patientFilter.name() != null && !patientFilter.name().isEmpty()) {
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + patientFilter.name().toLowerCase() + "%"));
            }

            if (patientFilter.cpf() != null && !patientFilter.cpf().isEmpty()) {
                predicates.add(builder.like(root.get("cpf"), StringUtils.onlyNumbers(patientFilter.cpf()) + "%"));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
