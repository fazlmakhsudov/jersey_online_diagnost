package com.practice.online_diagnost.repositories.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class RoleEntity {
    private int id;
    private String name;
}
