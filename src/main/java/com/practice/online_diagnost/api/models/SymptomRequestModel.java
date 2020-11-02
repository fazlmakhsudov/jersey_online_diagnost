package com.practice.online_diagnost.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SymptomRequestModel {
    private int id;
    private String name;
    private int diagnosesId;
    private int diseasesId;
}
