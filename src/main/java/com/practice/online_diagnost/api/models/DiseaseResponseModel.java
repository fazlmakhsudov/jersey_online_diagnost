package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class DiseaseResponseModel {
    private int id;
    private String name;
    private List<PatientResponseModel> patients;
    private List<SymptomResponseModel> symptoms;
    private Date createdDate;
}
