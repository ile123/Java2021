package com.ile.demo.persistance;

import lombok.Data;

@Data
public class Record {
    private Long id;
    private String readingTime;
    private Float readingValue;
    private Device device;
}