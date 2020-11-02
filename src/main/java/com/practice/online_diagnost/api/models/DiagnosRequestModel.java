package com.practice.online_diagnost.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosRequestModel {
    private int id;
    private String name;
    private int treatmentHistoriesId;

}
