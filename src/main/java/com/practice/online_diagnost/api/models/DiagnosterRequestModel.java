package com.practice.online_diagnost.api.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosterRequestModel {
    private String email;
    private List<QuestionRequestModel> questions;
}
