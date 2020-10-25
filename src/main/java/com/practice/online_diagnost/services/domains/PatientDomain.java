package com.practice.online_diagnost.services.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PatientDomain {
    private int id;
    private int diseasesId;
    private TreatmentHistoryDomain treatmentHistory;
    private UserDomain userDomain;
}