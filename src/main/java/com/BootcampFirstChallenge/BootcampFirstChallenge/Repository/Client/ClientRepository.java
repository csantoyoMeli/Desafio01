package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;

import java.util.List;

public interface ClientRepository {
    public void addNewClient(ClientDTO client) throws ClientException;
    public List<ClientDTO> getClients(List<Criterion> criteriaValues);
}
