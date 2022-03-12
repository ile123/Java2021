package com.ile.demo.persistance;

import lombok.Data;

@Data
public class Client{
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Device device;
}