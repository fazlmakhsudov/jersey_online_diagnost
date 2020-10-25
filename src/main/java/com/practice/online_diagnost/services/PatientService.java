package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.PatientDomain;

import java.util.List;

public interface PatientService extends BaseService<PatientDomain> {

    List<PatientDomain> findForDiseases(int diseasesId) throws ServiceException;

}
