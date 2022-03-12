package com.ile.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ile.demo.persistance.Client;
import com.ile.demo.persistance.Device;
import com.ile.demo.persistance.Record;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
//ZANEMARI OVAJ FILE SAMO GLEDAJ CONTROLLER
@Service
public class ClientService{
    public List<Client> GetData (String sort,String pageNum,String pageSize) throws IOException,InterruptedException {
        String urlString = "http://localhost:8080/client";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(urlString))
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clients = objectMapper.readValue(response.body(), new TypeReference<List<Client>>() {});
        return clients;
    }

    public void AddData(Long client_id , Long record_id, String date, Float value) throws IOException, InterruptedException {
        Client client = new Client();
        client.setId(client_id);
        Record record = new Record();
        record.setId(record_id);
        record.setReadingTime(date);
        record.setReadingValue(value);
        client.getDevice().getRecordList().add(record);
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(record);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type","application/json")
                .uri(URI.create("http://localhost:8080/records"+record_id+"/record"))
                .build();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        return;
    }
}