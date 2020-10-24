package com.practice.online_diagnost.services.factory;


import com.practice.online_diagnost.repositories.MySQLRoleRepositoryImpl;
import com.practice.online_diagnost.services.BaseService;
import com.practice.online_diagnost.services.RoleServiceImpl;

import java.util.logging.Logger;

/**
 * Factory class,
 * creates instances of Services
 *
 * @author Fazliddin Makhsudov
 */
public class ServiceFactory {
    private static final Logger LOG = Logger.getLogger("ServiceFactory");

    /**
     * Create Service intance
     *
     * @param serviceType service type
     * @return BaseService instance
     */
    public static BaseService createService(ServiceType serviceType) {
        LOG.info(String.format("creating instance of %s", serviceType));
        switch (serviceType) {
            case ROLE_SERVICE:
                return new RoleServiceImpl(new MySQLRoleRepositoryImpl());
            default:
                LOG.info(String.format("error, no service ->> %s", serviceType));
                throw new EnumConstantNotPresentException(ServiceType.class, serviceType.name());
        }
    }

}
