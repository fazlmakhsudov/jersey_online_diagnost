package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.QuestionRequestModel;
import com.practice.online_diagnost.repositories.entities.QuestionEntity;
import com.practice.online_diagnost.services.domains.QuestionDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionDomainBuilder {
    public QuestionDomain create(QuestionRequestModel questionModel) {
        return QuestionDomain.builder()
                .id(questionModel.getId())
                .name(Objects.isNull(questionModel.getName()) ?
                        "" : questionModel.getName())
                .answer(Objects.isNull(questionModel.getAnswer()) ?
                        "" : questionModel.getAnswer())
                .questionariesId(questionModel.getQuestionariesId())
                .build();
    }

    public List<QuestionDomain> create2(List<QuestionRequestModel> questionRequestModelList) {
        return questionRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public QuestionDomain create(QuestionEntity questionEntity) {
        return QuestionDomain.builder()
                .id(questionEntity.getId())
                .name(questionEntity.getName())
                .answer(questionEntity.getAnswer())
                .questionariesId(questionEntity.getQuestionariesId())
                .createdDate(questionEntity.getCreatedDate())
                .updatedDate(questionEntity.getUpdatedDate())
                .build();
    }

    public List<QuestionDomain> create(List<QuestionEntity> questionEntityList) {
        return questionEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
