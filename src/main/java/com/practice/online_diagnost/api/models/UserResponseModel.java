package com.practice.online_diagnost.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
public class UserResponseModel {
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

    private Date createdDate;
    private Date updatedDate;
}
