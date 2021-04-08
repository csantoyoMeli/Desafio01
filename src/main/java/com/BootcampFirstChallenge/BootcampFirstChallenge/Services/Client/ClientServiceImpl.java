package com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.NewClientResponseDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.StatusCodeDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Client.ClientRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    private final String[] avalibleParams = {"name", "age", "province"};
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public NewClientResponseDTO addNewClient(ClientDTO client) throws ClientException {

        // Assign a clientId in case it does not exist
        if (client.getClientId() == null)
            client.setClientId(RandomStringUtils.random(10, true, false));

        if (client.getAge() == 0
                || client.getName() == null
                || client.getProvince() == null)
            throw new ClientException(ClientException.VALUES_REQUIRED, ClientException.VALUES_REQUIRED_MSG);

        clientRepository.addNewClient(client);
        return new NewClientResponseDTO(client, new StatusCodeDTO(200,
                "SUCCESSFUL_OPERATION",
                "Se ha registrado un cliente nuevo correctamente"));
    }

    @Override
    public List<ClientDTO> getClients(Map<String, String> allParams) throws ClientException {
        List<Criterion> criteria = new ArrayList<>();
        if (allParams != null) {
            for (Map.Entry<String, String> entry : allParams.entrySet()) {
                // Validate params
                if (!Arrays.stream(avalibleParams).anyMatch(val -> val.equals(entry.getKey())))
                    throw new ClientException(ProductException.INVALID_INPUT, ProductException.INVALID_INPUT_MSG);

                // Add a criterion
                criteria.add(new Criterion(entry.getValue(), entry.getKey()));
            }
        }
        return clientRepository.getClients(criteria);
    }

}
