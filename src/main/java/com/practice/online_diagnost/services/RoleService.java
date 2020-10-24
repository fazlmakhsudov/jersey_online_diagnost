package com.practice.online_diagnost.services;

import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.RoleDomain;

public interface RoleService extends BaseService<RoleDomain> {
    RoleDomain find(String name) throws ServiceException;
}

