package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.DiagnosRequestModel;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.services.domains.DiagnosDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiagnosDomainBuilder {
    public DiagnosDomain create(DiagnosRequestModel diagnosModel) {
        return DiagnosDomain.builder()
                .id(diagnosModel.getId())
                .name(Objects.isNull(diagnosModel.getName())
                        ? "" : diagnosModel.getName())
                .treatmentHistoriesId(diagnosModel.getTreatmentHistoriesId())
                .build();
    }

    public List<DiagnosDomain> create2(List<DiagnosRequestModel> diagnosRequestModelList) {
        return diagnosRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }


    public DiagnosDomain create(DiagnosEntity diagnosEntity) {
        return DiagnosDomain.builder()
                .id(diagnosEntity.getId())
                .name(diagnosEntity.getName())
                .assignments(Objects.isNull(diagnosEntity.getAssignments()) ? new ArrayList<>() :
                        new AssignmentDomainBuilder().create(diagnosEntity.getAssignments()))
                .symptoms(Objects.isNull(diagnosEntity.getAssignments()) ? new ArrayList<>()
                        : new SymptomDomainBuilder().create(diagnosEntity.getSymptoms()))
                .treatmentHistoriesId(diagnosEntity.getTreatmentHistoriesId())
                .createdDate(diagnosEntity.getCreatedDate())
                .updatedDate(diagnosEntity.getUpdatedDate())
                .build();
    }

    public List<DiagnosDomain> create(List<DiagnosEntity> diagnosEntityList) {
        return diagnosEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
