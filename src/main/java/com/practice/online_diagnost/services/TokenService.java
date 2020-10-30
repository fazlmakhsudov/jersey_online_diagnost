package com.practice.online_diagnost.services;


public interface TokenService {
    String generate(String email);

    String generate(String email, int id);

    boolean validate(String token);

    int getId(String token);

    String getEmail(String token);
}
