package com.practice.online_diagnost.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentHistoryRequestModel {
    private int id;
    private int patientsId;
}
