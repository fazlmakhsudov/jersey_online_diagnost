package com.practice.online_diagnost.services;


public interface TokenService {
    String generate(String email);

    String generate(String email, int patientId);

    String generate(String email, int ownerId, int diagnosId);


    boolean validate(String token);

    int getOwnerId(String token);

    int getDiagnosId(String token);

    String getEmail(String token);
}
