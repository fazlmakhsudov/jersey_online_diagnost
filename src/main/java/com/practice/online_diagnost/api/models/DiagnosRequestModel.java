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
public class DiagnosRequestModel {
    private int id;
    private String name;
    private List<AssignmentRequestModel> assignments;
    private List<SymptomRequestModel> symptoms;
    private int treatmentHistoriesId;
    private Date createdDate;
    private Date updatedDate;
}
