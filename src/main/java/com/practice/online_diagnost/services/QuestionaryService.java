package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;

public interface QuestionaryService extends BaseService<QuestionaryDomain> {
    QuestionaryDomain find(String name) throws ServiceException;
}
