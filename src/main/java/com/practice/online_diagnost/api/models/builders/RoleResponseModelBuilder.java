package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.RoleResponseModel;
import com.practice.online_diagnost.services.domains.RoleDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleResponseModelBuilder {
    public RoleResponseModel create(RoleDomain roleDomain) {
        return RoleResponseModel.builder()
                .id(roleDomain.getId())
                .name(roleDomain.getName())
                .build();
    }

    public List<RoleResponseModel> create(List<RoleDomain> roleDomains) {
        return roleDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
