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
public class DiseaseRequestModel {
    private int id;
    private String name;
    private List<PatientRequestModel> patients;
    private List<SymptomRequestModel> symptoms;
    private Date createdDate;
}
