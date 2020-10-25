package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.AssignmentDomain;

import java.util.List;

public interface AssignmentService extends BaseService<AssignmentDomain> {
    AssignmentDomain find(String name) throws ServiceException;

    List<AssignmentDomain> findForDiagnoses(int diagnosesId) throws ServiceException;

    List<AssignmentDomain> findForMedics(int medicsId) throws ServiceException;
}
