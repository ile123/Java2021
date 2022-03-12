package com.ile.demo.persistance;

import lombok.Data;

import java.util.List;

@Data
public class Device{
    private Long id;
    private List<Record> recordList;
    private Client client;
}