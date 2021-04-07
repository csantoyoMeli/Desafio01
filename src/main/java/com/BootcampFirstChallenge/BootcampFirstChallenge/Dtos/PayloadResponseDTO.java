package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayloadResponseDTO {
    private TicketDTO ticket;
    private StatusCodeDTO statusCode;
}
