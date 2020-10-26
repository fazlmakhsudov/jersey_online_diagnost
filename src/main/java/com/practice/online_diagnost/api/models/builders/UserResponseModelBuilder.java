package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.UserResponseModel;
import com.practice.online_diagnost.services.domains.UserDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserResponseModelBuilder {
    public UserResponseModel create(UserDomain userDomain) {
        return UserResponseModel.builder()
                .id(userDomain.getId())
                .name(userDomain.getName())
                .surname(userDomain.getSurname())
                .email(userDomain.getEmail())
                .medicsId(userDomain.getMedicsId())
                .patientsId(userDomain.getPatientsId())
                .gender(userDomain.getGender())
                .location(userDomain.getLocation())
                .phone(userDomain.getPhone())
                .rolesId(userDomain.getRolesId())
                .birthdate(userDomain.getBirthdate())
                .createdDate(userDomain.getCreatedDate())
                .updatedDate(userDomain.getUpdatedDate())
                .build();
    }

    public List<UserResponseModel> create(List<UserDomain> userDomains) {
        return userDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
