package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public void addNewClient(ClientDTO client) throws ClientException {
        // Verify if ID already exists
        if (isClientAlreadyRegistered(client))
            throw new ClientException(ClientException.CLIENT_ALREADY_EXISTS, ClientException.CLIENT_ALREADY_EXISTS_MSG);

        FileWriter fileWriter = null;
        try {
            File file = ResourceUtils.getFile("classpath:dbClients.csv");
            fileWriter = new FileWriter(file, true);

            String data = "\n" + client.getClientId() +    // ID
                    "," + client.getName() +        // Name
                    "," + client.getAge() +        // Age
                    "," + client.getProvince(); // Province
            fileWriter.append(data);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close fileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<ClientDTO> getClients(List<Criterion> criteriaValues) {
        List<ClientDTO> clientsList = loadDataBase();
        // Apply Filters
        if (criteriaValues != null) {
            for (Criterion criterion : criteriaValues) {
                filterList(clientsList, criterion);
            }
        }
        return clientsList;
    }

    private List<ClientDTO> loadDataBase() {
        List<ClientDTO> clientsList = new ArrayList();

        BufferedReader bufferReader = null;
        try {
            // Open csv to read
            File file = ResourceUtils.getFile("classpath:dbClients.csv");
            bufferReader = new BufferedReader(new FileReader(file));
            // Read a line from file
            bufferReader.readLine();
            // Need data from second line of our file
            String line = bufferReader.readLine();

            while (line != null) {
                // Split lines
                String[] fields = line.split(",");

                // Creating a new ClientDTO
                ClientDTO clientDTO = new ClientDTO(
                        fields[0],    // ProductID
                        fields[1],  // Name
                        Integer.parseInt(fields[2]),  // CategoryName
                        fields[3]  // Brand
                );
                clientsList.add(clientDTO);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close bufferedReader
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clientsList;
    }

    private void filterList(List<ClientDTO> list, Criterion criterion) {
        switch (criterion.getType()) {
            case "name":    // Name Filter
                list.removeIf(clientDTO -> !clientDTO.getName().contains(criterion.getValue()));
                break;
            case "age":    // Price Filter
                int ageValue = Integer.parseUnsignedInt(criterion.getValue());
                list.removeIf(clientDTO -> clientDTO.getAge() > ageValue);
                break;
            case "province":    // Prestige Filter
                list.removeIf(clientDTO -> !clientDTO.getProvince().contains(criterion.getValue()));
                break;
        }
    }

    private boolean isClientAlreadyRegistered(ClientDTO client) {
        List<ClientDTO> clientsList = loadDataBase();
        ClientDTO clientRegistered = clientsList.stream()
                .filter(cli -> cli.getClientId().equals(client.getClientId()))
                .findFirst().orElse(null);

        if (clientRegistered == null) {
            clientRegistered = clientsList.stream()
                    .filter(cli -> (cli.getName().equals(client.getName())
                            && cli.getAge() == client.getAge()))
                    .findFirst().orElse(null);

            if (clientRegistered == null)
                return false;
        }
        return true;
    }
}
