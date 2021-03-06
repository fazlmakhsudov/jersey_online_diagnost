package com.practice.online_diagnost.repositories.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class MedicEntity {
    private int id;
    private String specialization;
    private List<AssignmentEntity> assignments;
    private UserEntity userEntity;
}
