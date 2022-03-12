package com.ile.service;

import com.ile.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.ile.repository.*;

@Service
@Transactional
public class ClientService{
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients(){
        return clientRepository.findAll();
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public Client getClient(Long id){
        return clientRepository.findById(id).get();
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

}