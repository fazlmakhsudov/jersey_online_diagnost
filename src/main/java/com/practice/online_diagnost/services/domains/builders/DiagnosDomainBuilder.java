package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.DiagnosRequestModel;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.services.domains.DiagnosDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiagnosDomainBuilder {
    public DiagnosDomain create(DiagnosRequestModel diagnosModel) {
        return DiagnosDomain.builder()
                .id(diagnosModel.getId())
                .name(diagnosModel.getName())
//                .assignments(Objects.isNull(diagnosModel.getAssignments()) ? null :
//                        new AssignmentDomainBuilder().create2(diagnosModel.getAssignments()))
//                .symptoms(Objects.isNull(diagnosModel.getAssignments()) ? null
//                        : new SymptomDomainBuilder().create2(diagnosModel.getSymptoms()))
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
                .assignments(Objects.isNull(diagnosEntity.getAssignments()) ? null :
                        new AssignmentDomainBuilder().create(diagnosEntity.getAssignments()))
                .symptoms(Objects.isNull(diagnosEntity.getAssignments()) ? null
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
