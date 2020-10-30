package com.practice.online_diagnost.services.domains;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentHistoryDomain {
    private int id;
    private int patientsId;
    private List<DiagnosDomain> diagnoses;
    private Date createdDate;
    private Date updatedDate;
}
