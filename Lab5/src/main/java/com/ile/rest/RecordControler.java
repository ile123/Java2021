package com.ile.rest;

import com.ile.persistence.Client;
import com.ile.persistence.Device;
import com.ile.persistence.Record;
import com.ile.service.ClientService;
import com.ile.service.DeviceService;
import com.ile.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@RestController
@RequestMapping("/records")
public class RecordControler{
    @Autowired
    private RecordService recordService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ClientService clientService;
    private Object String;

    @GetMapping("/{client_id}")
    public List<Record> list(@PathVariable Long client_id){
        return clientService.getClient(client_id).getDevice().getRecordList();
    }

    @GetMapping("/{client_id}/getRecord/{record_id}")
    public Record getRecord(@PathVariable Long client_id,@PathVariable Long record_id){
        int id = Math.toIntExact(record_id);
        return clientService.getClient(client_id).getDevice().getRecordList().get(id);
    }

    @PostMapping("/{client_id}")
    public ResponseEntity<Record> add(@PathVariable Long client_id,@RequestBody Record record){
        List<Record> allRecords = clientService.getClient(client_id).getDevice().getRecordList();
        String[] stringPartsRecord = record.getReadingTime().split("-");
        for (Record allRecord : allRecords) {
            if(allRecord.getReadingTime()==null){ break; }
            String[] stringPartsList = allRecord.getReadingTime().split("-");
            if ((stringPartsList[1].equals(stringPartsRecord[1])) && (stringPartsRecord[2].equals(stringPartsList[2]))) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        Device device = clientService.getClient(client_id).getDevice();
        device.getRecordList().add(record);
        record.setDevice(device);
        deviceService.saveDevice(device);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{client_id}/change/{record_id}")
    public ResponseEntity<Record> update(@RequestBody Record record,@PathVariable Long client_id,@PathVariable Long record_id){
        try{
            for(int i =0;i<clientService.getClient(client_id).getDevice().getRecordList().size();i++){
                Long recordListID = clientService.getClient(client_id).getDevice().getRecordList().get(i).getId();
                if(record_id.equals(recordListID)){
                    clientService.getClient(client_id).getDevice().getRecordList().get(i).setReadingTime(record.getReadingTime());
                    clientService.getClient(client_id).getDevice().getRecordList().get(i).setReadingValue(record.getReadingValue());
                    break;
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id){ recordService.deleteRecord(id); }
}