package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.QuestionaryRequestModel;
import com.practice.online_diagnost.repositories.entities.QuestionaryEntity;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionaryDomainBuilder {
    public QuestionaryDomain create(QuestionaryRequestModel questionaryModel) {
        return QuestionaryDomain.builder()
                .id(questionaryModel.getId())
                .name(Objects.isNull(questionaryModel.getName()) ?
                        "" :questionaryModel.getName())

                .build();
    }

    public List<QuestionaryDomain> create2(List<QuestionaryRequestModel> questionaryRequestModelList) {
        return questionaryRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public QuestionaryDomain create(QuestionaryEntity questionaryEntity) {
        return QuestionaryDomain.builder()
                .id(questionaryEntity.getId())
                .name(questionaryEntity.getName())
                .questions(Objects.isNull(questionaryEntity.getQuestions()) ? new ArrayList<>()
                        : new QuestionDomainBuilder().create(questionaryEntity.getQuestions()))
                .createdDate(questionaryEntity.getCreatedDate())
                .updatedDate(questionaryEntity.getUpdatedDate())
                .build();
    }

    public List<QuestionaryDomain> create(List<QuestionaryEntity> questionaryEntityList) {
        return questionaryEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
