package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.MedicResponseModel;
import com.practice.online_diagnost.services.domains.MedicDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MedicResponseModelBuilder {
    public MedicResponseModel create(MedicDomain medicDomain) {
        return MedicResponseModel.builder()
                .id(medicDomain.getId())
                .assignments(new AssignmentResponseModelBuilder().create(medicDomain.getAssignments()))
                .specialization(medicDomain.getSpecialization())
                .user(new UserResponseModelBuilder().create(medicDomain.getUserDomain()))
                .build();
    }

    public List<MedicResponseModel> create(List<MedicDomain> medicDomains) {
        return medicDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
