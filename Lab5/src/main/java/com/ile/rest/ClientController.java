package com.ile.rest;
import com.ile.persistence.Client;
import com.ile.persistence.Record;
import com.ile.repository.ClientRepository;
import com.ile.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("")
    public Client[] list(){
        return clientService.listAllClients().toArray(Client[]::new);
    }

    @GetMapping("/page")
    public ResponseEntity<Client[]> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        try{
            Client[] clients = new Client[1000];
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<Client> pageClients;
            pageClients = clientRepository.findAll(pageable);
            clients = pageClients.getContent().toArray(new Client[0]);
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id){
        try{
            Client client = clientService.getClient(id);
            return new ResponseEntity<Client>(client,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Client> add(@RequestBody Client client){
        Long clientID = client.getId();
        List<Client> clientList = clientService.listAllClients();
        for(int i=0;i<clientList.size();i++){
            if(Objects.equals(client.getAddress(), clientList.get(i).getAddress())){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        clientService.saveClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client,@PathVariable Long id){
        try{
            Client exitClient = clientService.getClient(id);
            client.setId(id);
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}