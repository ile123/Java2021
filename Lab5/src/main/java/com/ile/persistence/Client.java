package com.ile.persistence;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

//Springboot generira sve potrebne stvari za SQL pa netribas se stime zajebavat
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","device"})
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String firstName;
    String lastName;
    String address;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "client")
    @JoinColumn(name = "device_id")
    private Device device;

    public Client(){

    }
}

