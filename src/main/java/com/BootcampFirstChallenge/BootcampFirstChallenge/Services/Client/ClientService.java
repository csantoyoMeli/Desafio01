package com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.NewClientResponseDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;

import java.util.List;
import java.util.Map;

public interface ClientService {
    public NewClientResponseDTO addNewClient(ClientDTO client) throws ClientException;
    public List<ClientDTO> getClients(Map<String, String> allParams) throws ClientException;
}
