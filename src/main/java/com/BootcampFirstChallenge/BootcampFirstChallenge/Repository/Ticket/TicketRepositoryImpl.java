package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Ticket;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client.ClientDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PurchaseProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Ticket.TicketDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    @Override
    public List<TicketDTO> getTickets() {
        List<TicketDTO> clientsList = loadDataBase();
        return clientsList;
    }

    @Override
    public void addNewTicket(TicketDTO ticket) {
        FileWriter fileWriter = null;

        try {
            File file = ResourceUtils.getFile("classpath:dbTickets.csv");
            fileWriter = new FileWriter(file, true);

            String productsIds = "";
            for (PurchaseProductDTO article : ticket.getArticles()) {
                productsIds += "_" + article.getProductId()
                    + ":" + article.getName()
                    + ":" + article.getBrand()
                    + ":" + article.getQuantity();
            }
            String data = "\n" + ticket.getId() + "," + productsIds.substring(1) + "," + ticket.getTotal();
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

    private List<TicketDTO> loadDataBase() {
        List<TicketDTO> ticketList = new ArrayList();

        BufferedReader bufferReader = null;
        try {
            // Open csv to read
            File file = ResourceUtils.getFile("classpath:dbTickets.csv");
            bufferReader = new BufferedReader(new FileReader(file));
            // Read a line from file
            bufferReader.readLine();
            // Need data from second line of our file
            String line = bufferReader.readLine();

            while (line != null) {
                // Split lines
                String[] fields = line.split(",");

                // Split lines to build PurchaseProduct
                String[] purchaseFields = fields[1].split("_");
                List<PurchaseProductDTO> articles = new ArrayList<>();
                for (String pf: purchaseFields){
                    String[] articleFields = pf.split(":");
                    articles.add(new PurchaseProductDTO(Integer.parseInt(articleFields[0]), // ID
                            articleFields[1],   // Name
                            articleFields[2],   // Brand
                            Integer.parseInt(articleFields[3])  //Quantity
                            ));
                }

                // Creating a new ClientDTO
                TicketDTO ticketDTO = new TicketDTO(
                        Long.parseLong(fields[0]),    // TicketID
                        articles,
                        Double.parseDouble(fields[2]) // Brand
                );
                ticketList.add(ticketDTO);
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
        return ticketList;
    }
}
