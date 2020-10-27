package com.practice.online_diagnost.api.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestModel {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private int patientsId;
    private int medicsId;
    private int rolesId;
    private String gender;
    private String location;
    private Date birthdate;
}
