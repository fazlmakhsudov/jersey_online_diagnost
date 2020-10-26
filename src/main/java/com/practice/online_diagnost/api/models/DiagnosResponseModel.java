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
public class DiagnosResponseModel {
    private int id;
    private String name;
    private List<AssignmentResponseModel> assignments;
    private List<SymptomResponseModel> symptoms;
    private int treatmentHistoriesId;
    private Date createdDate;
    private Date updatedDate;
}
