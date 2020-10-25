package com.practice.online_diagnost.services.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class RoleDomain {
    private int id;
    private String name;
}