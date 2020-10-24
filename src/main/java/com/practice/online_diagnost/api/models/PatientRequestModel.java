package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PatientRequestModel {
    private int id;
    private int diseasesId;
    private TreatmentHistoryRequestModel treatmentHistory;
    private UserRequestModel userDomain;
}
