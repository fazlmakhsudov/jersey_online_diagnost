package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.PatientResponseModel;
import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.services.domains.PatientDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PatientResponseModelBuilder {
    public PatientResponseModel create(PatientDomain patientDomain) {
        return PatientResponseModel.builder()
                .id(patientDomain.getId())
                .condition(patientDomain.getCondition())
                .diseasesId(patientDomain.getDiseasesId())
                .treatmentHistory(new TreatmentHistoryResponseModelBuilder().create(patientDomain.getTreatmentHistory()))
                .userDomain(new UserResponseModelBuilder().create(patientDomain.getUserDomain()))
                .build();
    }

    public List<PatientResponseModel> create(List<PatientDomain> patientDomains) {
        return patientDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
