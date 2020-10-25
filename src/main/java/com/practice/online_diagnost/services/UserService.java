package com.practice.online_diagnost.services;

import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.UserDomain;

import java.util.List;

public interface UserService extends BaseService<UserDomain> {
    UserDomain find(String email) throws ServiceException;

    UserDomain findForPatients(int patientsId) throws ServiceException;

    UserDomain findForMedics(int medicsId) throws ServiceException;

    List<UserDomain> findForRoles(int rolesId) throws ServiceException;
}
