package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TreatmentHistoryRequestModel {
    private int id;
    private int patientsId;
}
