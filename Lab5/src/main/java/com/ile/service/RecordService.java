package com.ile.service;

import com.ile.persistence.*;
import com.ile.persistence.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.ile.repository.*;

@Service
@Transactional
public class RecordService {
    @Autowired
    private RecordRepositor recordRepositor;

    public List<Record> listAllRecords(){
        return recordRepositor.findAll();
    }

    public void saveRecord(Record record){ recordRepositor.save(record); }

    public Record getRecord(Long id){
        return recordRepositor.findById(id).get();
    }

    public void deleteRecord(Long id){
        recordRepositor.deleteById(id);
    }
}