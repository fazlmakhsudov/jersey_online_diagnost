package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class MedicResponseModel {
    private int id;
    private String specialization;
    private List<AssignmentResponseModel> assignments;
    private UserResponseModel user;
}
