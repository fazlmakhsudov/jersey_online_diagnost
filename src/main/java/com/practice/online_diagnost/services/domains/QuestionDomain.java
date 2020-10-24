package com.practice.online_diagnost.services.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
public class QuestionDomain {
    private int id;
    private String name;
    private String answer;
    private int questionariesId;
    private Date createdDate;
    private Date updatedDate;
}
