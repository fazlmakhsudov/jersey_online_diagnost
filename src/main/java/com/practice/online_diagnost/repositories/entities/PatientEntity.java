package com.practice.online_diagnost.repositories.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PatientEntity {
    private int id;
    private int diseasesId;
    private String condition;
}
