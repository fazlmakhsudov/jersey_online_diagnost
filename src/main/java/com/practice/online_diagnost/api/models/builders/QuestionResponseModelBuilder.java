package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.QuestionResponseModel;
import com.practice.online_diagnost.services.domains.QuestionDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionResponseModelBuilder {
    public QuestionResponseModel create(QuestionDomain questionDomain) {
        return QuestionResponseModel.builder()
                .id(questionDomain.getId())
                .name(questionDomain.getName())
                .answer(questionDomain.getAnswer())
                .questionariesId(questionDomain.getQuestionariesId())
                .createdDate(questionDomain.getCreatedDate())
                .updatedDate(questionDomain.getUpdatedDate())
                .build();
    }

    public List<QuestionResponseModel> create(List<QuestionDomain> questionDomains) {
        return questionDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
