package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.UserEntity;
import com.practice.online_diagnost.services.domains.UserDomain;

import java.util.List;
import java.util.stream.Collectors;

public class UserEntityBuilder {
    public UserEntity create(UserDomain userDomain) {
        return UserEntity.builder()
                .id(userDomain.getId())
                .name(userDomain.getName())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .surname(userDomain.getSurname())
                .medicsId(userDomain.getMedicsId())
                .patientsId(userDomain.getPatientsId())
                .rolesId(userDomain.getRolesId())
                .phone(userDomain.getPhone())
                .createdDate(userDomain.getCreatedDate())
                .updatedDate(userDomain.getUpdatedDate())
                .build();
    }

    public List<UserEntity> create(List<UserDomain> userDomainList) {
        return userDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

}
