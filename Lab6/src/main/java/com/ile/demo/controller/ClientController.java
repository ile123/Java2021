package com.ile.demo.controller;

import com.ile.demo.persistance.Client;
import com.ile.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String ShowClients(Model model){
        Client[] response = restTemplate.getForObject("http://localhost:8080/client",Client[].class);
        System.out.println("JELI OVO RADI");
        for(Client m: response){
            System.out.println(m.getFirstName());
        }
        model.addAttribute("clients",response);
        return "index";
    }

    @GetMapping("/addClient")
    public String showClientForm(Model model) {
        Client client=new Client();
        model.addAttribute("measure",client);
        return "new_client";
    }
}