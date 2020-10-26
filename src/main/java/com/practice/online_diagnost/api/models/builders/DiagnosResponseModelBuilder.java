package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.DiagnosResponseModel;
import com.practice.online_diagnost.services.domains.DiagnosDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DiagnosResponseModelBuilder {
    public DiagnosResponseModel create(DiagnosDomain diagnosDomain) {
        return DiagnosResponseModel.builder()
                .id(diagnosDomain.getId())
                .name(diagnosDomain.getName())
                .assignments(new AssignmentResponseModelBuilder().create(diagnosDomain.getAssignments()))
                .symptoms(new SymptomResponseModelBuilder().create(diagnosDomain.getSymptoms()))
                .treatmentHistoriesId(diagnosDomain.getTreatmentHistoriesId())
                .createdDate(diagnosDomain.getCreatedDate())
                .updatedDate(diagnosDomain.getUpdatedDate())
                .build();
    }

    public List<DiagnosResponseModel> create(List<DiagnosDomain> diagnosDomains) {
        return diagnosDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
