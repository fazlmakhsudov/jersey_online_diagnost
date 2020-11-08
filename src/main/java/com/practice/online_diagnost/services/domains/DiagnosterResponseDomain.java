package com.practice.online_diagnost.services.domains;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosterResponseDomain {
    private String diseaseName;
    private double probability;
    private int patientsId;
    private String symptoms;
    private Date createdDate;
}
