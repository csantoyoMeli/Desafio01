package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Ticket.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MarketCarDTO {
    private List<TicketDTO> tickets;
    private double totalCost;
}
