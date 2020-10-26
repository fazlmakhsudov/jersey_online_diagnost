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
public class QuestionResponseModel {
    private int id;
    private String name;
    private String answer;
    private int questionariesId;
    private Date createdDate;
    private Date updatedDate;
}
