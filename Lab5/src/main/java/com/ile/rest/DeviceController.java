package com.ile.rest;
import com.ile.persistence.Client;
import com.ile.persistence.Device;
import com.ile.service.ClientService;
import com.ile.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    ClientService clientService;

    @GetMapping("")
    public List<Device> list(){
        return deviceService.listAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> get(@PathVariable Long id){
        try{
            Device device = deviceService.getDevice(id);
            return new ResponseEntity<Device>(device,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Device>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public void add(@PathVariable Long id){
        Device device = new Device();
        Client client = clientService.getClient(id);
        client.setDevice(device);
        device.setClient(client);
        clientService.saveClient(client);
    }

    @PutMapping("/{client_id}/connect/{device_id}")
    public ResponseEntity<Device> update(@PathVariable Long client_id, @PathVariable Long device_id){
        try{
            Client client = clientService.getClient(client_id);
            Device device = deviceService.getDevice(device_id);
            device.setClient(client);
            client.setDevice(device);
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ deviceService.deleteDevice(id); }
}