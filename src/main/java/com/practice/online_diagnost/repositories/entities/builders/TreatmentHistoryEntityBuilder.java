package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.TreatmentHistoryEntity;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;

import java.util.List;
import java.util.stream.Collectors;

public class TreatmentHistoryEntityBuilder {
    public TreatmentHistoryEntity create(TreatmentHistoryDomain treatmentHistoryDomain) {
        return TreatmentHistoryEntity.builder()
                .id(treatmentHistoryDomain.getId())
                .diagnoses(new DiagnosEntityBuilder().create(treatmentHistoryDomain.getDiagnoses()))
                .patientsId(treatmentHistoryDomain.getPatientsId())
                .createdDate(treatmentHistoryDomain.getCreatedDate())
                .updatedDate(treatmentHistoryDomain.getUpdatedDate())
                .build();
    }


    public List<TreatmentHistoryEntity> create(List<TreatmentHistoryDomain> treatmentHistoryDomainList) {
        return treatmentHistoryDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
