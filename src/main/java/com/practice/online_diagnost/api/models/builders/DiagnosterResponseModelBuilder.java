package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.DiagnosterResponseModel;
import com.practice.online_diagnost.services.domains.DiagnosterResponseDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DiagnosterResponseModelBuilder {
    public DiagnosterResponseModel create(DiagnosterResponseDomain diagnosterResponseDomain) {
        return DiagnosterResponseModel.builder()
                .diseaseName(diagnosterResponseDomain.getDiseaseName())
                .probability(diagnosterResponseDomain.getProbability())
                .patientsId(diagnosterResponseDomain.getPatientsId())
                .symptoms(diagnosterResponseDomain.getSymptoms())
                .createdDate(diagnosterResponseDomain.getCreatedDate())
                .build();
    }

    public List<DiagnosterResponseModel> create(List<DiagnosterResponseDomain> diagnosterResponseDomains) {
        return diagnosterResponseDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
