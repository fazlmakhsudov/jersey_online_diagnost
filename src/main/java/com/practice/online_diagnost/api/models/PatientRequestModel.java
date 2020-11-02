package com.practice.online_diagnost.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestModel {
    private int id;
    private int diseasesId;
    private String condition;
}
