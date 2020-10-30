package com.practice.online_diagnost.api.models;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentHistoryResponseModel {
    private int id;
    private int patientsId;
    private List<DiagnosResponseModel> diagnoses;
    private Date createdDate;
    private Date updatedDate;
}
