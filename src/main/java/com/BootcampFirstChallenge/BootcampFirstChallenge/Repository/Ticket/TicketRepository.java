package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Ticket;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.TicketDTO;

import java.io.IOException;
import java.util.List;

public interface TicketRepository {
    public List<TicketDTO> getTickets();
    public void addNewTicket(TicketDTO ticket);
}
