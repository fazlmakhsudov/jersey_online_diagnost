package com.practice.online_diagnost.services.domains;

import com.practice.online_diagnost.repositories.entities.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class RoleDomain {
    private int id;
    private String name;
}