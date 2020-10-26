package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.QuestionaryResponseModel;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionaryResponseModelBuilder {
    public QuestionaryResponseModel create(QuestionaryDomain questionaryDomain) {
        return QuestionaryResponseModel.builder()
                .id(questionaryDomain.getId())
                .name(questionaryDomain.getName())
                .questions(new QuestionResponseModelBuilder().create(questionaryDomain.getQuestions()))
                .createdDate(questionaryDomain.getCreatedDate())
                .updatedDate(questionaryDomain.getUpdatedDate())
                .build();
    }

    public List<QuestionaryResponseModel> create(List<QuestionaryDomain> questionaryDomains) {
        return questionaryDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
