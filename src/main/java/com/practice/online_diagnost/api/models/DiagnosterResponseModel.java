package com.practice.online_diagnost.api.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosterResponseModel {
    private String diseaseName;
    private double probability;
    private int patientsId;
    private String symptoms;
    private Date createdDate;
}
