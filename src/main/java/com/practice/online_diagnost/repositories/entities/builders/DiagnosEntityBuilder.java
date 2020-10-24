package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.services.domains.DiagnosDomain;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosEntityBuilder {
    public DiagnosEntity create(DiagnosDomain diagnosDomain) {
        return DiagnosEntity.builder()
                .id(diagnosDomain.getId())
                .name(diagnosDomain.getName())
                .treatmentHistoriesId(diagnosDomain.getTreatmentHistoriesId())
                .assignments(new AssignmentEntityBuilder().create(diagnosDomain.getAssignments()))
                .symptoms(new SymptomEntityBuilder().create(diagnosDomain.getSymptoms()))
                .createdDate(diagnosDomain.getCreatedDate())
                .updatedDate(diagnosDomain.getUpdatedDate())
                .build();
    }

    public List<DiagnosEntity> create(List<DiagnosDomain> diagnosDomainList) {
        return diagnosDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
