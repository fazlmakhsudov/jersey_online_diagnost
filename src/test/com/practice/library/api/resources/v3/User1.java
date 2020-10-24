package com.practice.online_diagnost.api.resources.v3;

import lombok.Getter;

@Getter
public class User1 {
    String id;
    String firstName;
    String lastName;
    String login;
    String password;

    public User1(String id, String firstName, String lastName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
}
