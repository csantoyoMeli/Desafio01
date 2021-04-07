package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TicketDTO {
    private long id;
    private List<PurchaseProductDTO> articles;
    private double total;
}
