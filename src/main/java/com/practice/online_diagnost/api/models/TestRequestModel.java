package com.practice.online_diagnost.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class TestRequestModel {
    private int id;
    private String name;
    private double age;
    private List<Date> dates;
}
