package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MedicRequestModel {
    private int id;
    private String specialization;
}
