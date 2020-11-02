package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class TreatmentHistoryResponseModelBuilder {
    public TreatmentHistoryResponseModel create(TreatmentHistoryDomain treatmentHistoryDomain) {
        return Objects.isNull(treatmentHistoryDomain) ? null :
                TreatmentHistoryResponseModel.builder()
                        .id(treatmentHistoryDomain.getId())
                        .diagnoses(new DiagnosResponseModelBuilder().create(treatmentHistoryDomain.getDiagnoses()))
                        .patientsId(treatmentHistoryDomain.getPatientsId())
                        .createdDate(treatmentHistoryDomain.getCreatedDate())
                        .updatedDate(treatmentHistoryDomain.getUpdatedDate())
                        .build();
    }

    public List<TreatmentHistoryResponseModel> create(List<TreatmentHistoryDomain> treatmentHistoryDomains) {
        return treatmentHistoryDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
