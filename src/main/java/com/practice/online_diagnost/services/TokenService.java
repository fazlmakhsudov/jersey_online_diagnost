package com.practice.online_diagnost.services;


public interface TokenService {
    String generate(String email);

    boolean validate(String token);
}
