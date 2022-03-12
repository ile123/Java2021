package com.ile.service;

import com.ile.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Record;
import java.util.List;

import com.ile.repository.*;

@Service
@Transactional
public class DeviceService{
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> listAllDevices(){
        return deviceRepository.findAll();
    }

    public void saveDevice(Device device){
        deviceRepository.save(device);
    }

    public Device getDevice(Long id){
        return deviceRepository.findById(id).get();
    }

    public void deleteDevice(Long id){
        deviceRepository.deleteById(id);
    }

}