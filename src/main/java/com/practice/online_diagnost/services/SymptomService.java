package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.SymptomDomain;

import java.util.List;

public interface SymptomService extends BaseService<SymptomDomain> {
    SymptomDomain find(String name) throws ServiceException;

    List<SymptomDomain> findForDiagnoses(int diagnosesId) throws ServiceException;

    List<SymptomDomain> findForDiseases(int diseasesId) throws ServiceException;
}
