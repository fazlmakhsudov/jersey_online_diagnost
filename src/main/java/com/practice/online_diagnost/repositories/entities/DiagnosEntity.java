package com.practice.online_diagnost.repositories.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class DiagnosEntity {
    private int id;
    private String name;
    private List<AssignmentEntity> assignments;
    private List<SymptomEntity> symptoms;
    private int treatmentHistoriesId;
    private Date createdDate;
    private Date updatedDate;
}
