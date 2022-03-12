package com.ile.rest;

import com.ile.persistence.Record;
import com.ile.repository.ClientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;


@Data
@RestController
@RequestMapping("/query")
public class QuerryController{
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client/{id}/year")
    public ResponseEntity<String> queryYear(@RequestParam(name = "year") int year, @PathVariable Long id){
        List<Record> allRecords = clientRepository.getById(id).getDevice().getRecordList();
        String total= "Total for the year: ";
        float sum = 0;
        try{
            for (Record allRecord : allRecords) {
                String[] date = allRecord.getReadingTime().split("-");
                int yearDate = Integer.parseInt(date[2]);
                if (year==yearDate) {
                    sum += allRecord.getReadingValue();
                }
            }
            total+=sum;
            return new ResponseEntity<>(total,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/client/{id}/byMonth")
    public ResponseEntity<HashMap<String,Float>> queryByMonth(@RequestParam(name="year") int year,@PathVariable Long id){
        HashMap<String,Float> recordByMonth = new LinkedHashMap<>();
        List<Record> recordList = clientRepository.getById(id).getDevice().getRecordList();
        for(int i=1;i<=12;i++){
            recordByMonth.put(Integer.toString(i)+".", 0.0F);
        }
        List<Record> filteredList = new ArrayList<>();
        for (Record record : recordList) {
            String[] parsedString = record.getReadingTime().split("-");
            int yearString = Integer.parseInt(parsedString[2]);
            if (year == yearString) {
                filteredList.add(record);
            }
        }
        if(filteredList.isEmpty()){ return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        for (Record record : filteredList) {
            String[] parsedString = record.getReadingTime().split("-");
            switch (parsedString[1]) {
                case "1":
                    recordByMonth.replace("1.", record.getReadingValue());
                    break;
                case "2":
                    recordByMonth.replace("2.", record.getReadingValue());
                    break;
                case "3":
                    recordByMonth.replace("3.", record.getReadingValue());
                    break;
                case "4":
                    recordByMonth.replace("4.", record.getReadingValue());
                    break;
                case "5":
                    recordByMonth.replace("5.", record.getReadingValue());
                    break;
                case "6":
                    recordByMonth.replace("6.", record.getReadingValue());
                    break;
                case "7":
                    recordByMonth.replace("7.", record.getReadingValue());
                    break;
                case "8":
                    recordByMonth.replace("8.", record.getReadingValue());
                    break;
                case "9":
                    recordByMonth.replace("9.", record.getReadingValue());
                    break;
                case "10":
                    recordByMonth.replace("10.", record.getReadingValue());
                    break;
                case "11":
                    recordByMonth.replace("11.", record.getReadingValue());
                    break;
                case "12":
                    recordByMonth.replace("12.", record.getReadingValue());
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(recordByMonth,HttpStatus.OK);
    }

    @GetMapping("/client/{id}/certainMonth")
    public ResponseEntity<String> queryMonth(@RequestParam(name="year") int year,@RequestParam(name="month") int month,@PathVariable Long id){
        List<Record> recordList = clientRepository.getById(id).getDevice().getRecordList();
        String string = "";
        switch (month){
            case 1:
                string+="January: ";
                break;
            case 2:
                string+="Febuary: ";
                break;
            case 3:
                string+="March: ";
                break;
            case 4:
                string+="April: ";
                break;
            case 5:
                string+="May: ";
                break;
            case 6:
                string+="June: ";
                break;
            case 7:
                string+="July: ";
            case 8:
                string+="August: ";
                break;
            case 9:
                string+="September: ";
                break;
            case 10:
                string+="October: ";
                break;
            case 11:
                string+="November: ";
                break;
            case 12:
                string+="December: ";
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Record record : recordList) {
            String[] parsedString = record.getReadingTime().split("-");
            int yearString = Integer.parseInt(parsedString[2]);
            int monthString = Integer.parseInt(parsedString[1]);
            if (yearString == year) {
                if (monthString == month) {
                    string += record.getReadingValue();
                    return new ResponseEntity<>(string, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}