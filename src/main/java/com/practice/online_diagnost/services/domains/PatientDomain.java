package com.practice.online_diagnost.services.domains;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDomain {
    private int id;
    private int diseasesId;
    private String condition;
    private TreatmentHistoryDomain treatmentHistory;
    private UserDomain userDomain;
}
