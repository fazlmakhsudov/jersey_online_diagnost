package com.practice.online_diagnost.services;

import com.practice.online_diagnost.services.domains.UserDomain;

public interface UserService extends BaseService<UserDomain> {
    UserDomain find(String name);
}

