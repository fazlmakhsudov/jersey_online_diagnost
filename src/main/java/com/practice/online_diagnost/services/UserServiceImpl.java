//package com.practice.online_diagnost.services;
//
//import com.practice.online_diagnost.repositories.UserRepository;
//import com.practice.online_diagnost.repositories.entities.builders.UserEntityBuilder;
//import com.practice.online_diagnost.services.domains.UserDomain;
//import com.practice.online_diagnost.services.domains.builders.UserDomainBuilder;
//
//import java.util.List;
//
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//    private final UserDomainBuilder userDomainBuilder;
//    private final UserEntityBuilder userEntityBuilder;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//        this.userEntityBuilder = new UserEntityBuilder();
//        this.userDomainBuilder = new UserDomainBuilder();
//    }
//
//    @Override
//    public int add(UserDomain item) {
//        return userRepository.create(userEntityBuilder.create(item));
//    }
//
//    @Override
//    public UserDomain find(int id) {
//        return userDomainBuilder.create(userRepository.read(id));
//    }
//
//    @Override
//    public UserDomain find(String name) {
//        return userDomainBuilder.create(userRepository.read(name));
//    }
//
//    @Override
//    public boolean save(UserDomain item) {
//        return userRepository.update(userEntityBuilder.create(item));
//    }
//
//    @Override
//    public boolean remove(int id) {
//        return userRepository.delete(id);
//    }
//
//    @Override
//    public List<UserDomain> findAll() {
//        return userDomainBuilder.create(userRepository.readAll());
//    }
//}
