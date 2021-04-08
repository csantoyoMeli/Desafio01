package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Ticket;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PurchaseProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Ticket.TicketDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    @Override
    public List<TicketDTO> getTickets() {
        return null;
    }

    @Override
    public void addNewTicket(TicketDTO ticket) {
        FileWriter fileWriter = null;

        try {
            File file = ResourceUtils.getFile("classpath:dbTickets.csv");
            fileWriter = new FileWriter(file, true);

            String productsIds = "|";
            for(PurchaseProductDTO article: ticket.getArticles()) {
                productsIds += article.getProductId() + "|";
            }
            String data = ticket.getId() + "," + productsIds + "," + ticket.getTotal() + "\n";
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
}
