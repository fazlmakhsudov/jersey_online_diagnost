package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
public class SymptomResponseModel {
    private int id;
    private String name;
    private int diagnosesId;
    private int diseasesId;
    private Date createdDate;
    private Date updatedDate;
}
