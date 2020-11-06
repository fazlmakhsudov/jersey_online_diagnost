package com.practice.online_diagnost.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosterResponseModel {
    private String diseaseName;
    private double probability;
}
