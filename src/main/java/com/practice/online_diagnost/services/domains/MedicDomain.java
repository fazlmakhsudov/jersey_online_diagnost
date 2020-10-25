package com.practice.online_diagnost.services.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class MedicDomain {
    private int id;
    private String specialization;
    private List<AssignmentDomain> assignments;
    private UserDomain userDomain;
}
