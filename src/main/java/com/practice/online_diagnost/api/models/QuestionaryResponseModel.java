package com.practice.online_diagnost.api.models;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionaryResponseModel {
    private int id;
    private String name;
    private List<QuestionResponseModel> questions;
    private Date createdDate;
    private Date updatedDate;
}
