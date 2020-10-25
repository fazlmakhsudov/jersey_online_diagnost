package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.QuestionEntity;
import com.practice.online_diagnost.services.domains.QuestionDomain;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionEntityBuilder {
    public QuestionEntity create(QuestionDomain questionDomain) {
        return QuestionEntity.builder()
                .id(questionDomain.getId())
                .name(questionDomain.getName())
                .answer(questionDomain.getAnswer())
                .questionariesId(questionDomain.getQuestionariesId())
                .createdDate(questionDomain.getCreatedDate())
                .updatedDate(questionDomain.getUpdatedDate())
                .build();
    }

    public List<QuestionEntity> create(List<QuestionDomain> questionDomainList) {
        return questionDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
