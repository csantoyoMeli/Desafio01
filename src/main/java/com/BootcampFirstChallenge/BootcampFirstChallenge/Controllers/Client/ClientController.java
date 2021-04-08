package com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/addClient")
    public ResponseEntity addNewClient(@RequestBody ClientDTO client) throws ClientException {
        return new ResponseEntity(clientService.addNewClient(client), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity getClients(@RequestParam(required = false) Map<String, String> allParams) throws ClientException {
        return new ResponseEntity(clientService.getClients(allParams), HttpStatus.OK);
    }
}
