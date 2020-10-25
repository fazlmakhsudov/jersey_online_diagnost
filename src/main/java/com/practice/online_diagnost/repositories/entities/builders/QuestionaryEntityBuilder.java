package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.QuestionaryEntity;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionaryEntityBuilder {
    public QuestionaryEntity create(QuestionaryDomain questionaryDomain) {
        return QuestionaryEntity.builder()
                .id(questionaryDomain.getId())
                .name(questionaryDomain.getName())
                .questions(new QuestionEntityBuilder().create(questionaryDomain.getQuestions()))
                .createdDate(questionaryDomain.getCreatedDate())
                .updatedDate(questionaryDomain.getUpdatedDate())
                .build();
    }

    public List<QuestionaryEntity> create(List<QuestionaryDomain> questionaryDomainList) {
        return questionaryDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
