package com.practice.online_diagnost.api.models;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestModel {
    private int id;
    private String name;
    private String answer;
    private int questionariesId;
}
