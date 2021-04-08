package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.StatusCodeDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Ticket.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayloadResponseDTO {
    private TicketDTO ticket;
    private StatusCodeDTO statusCode;
}
