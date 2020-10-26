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
public class TreatmentHistoryResponseModel {
    private int id;
    private int patientsId;
    private List<DiagnosResponseModel> diagnoses;
    private Date createdDate;
    private Date updatedDate;
}
