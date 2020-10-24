package com.practice.online_diagnost.services.domains;

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
public class DiagnosDomain {
    private int id;
    private String name;
    private List<AssignmentDomain> assignments;
    private List<SymptomDomain> symptoms;
    private int treatmentHistoriesId;
    private Date createdDate;
    private Date updatedDate;
}
