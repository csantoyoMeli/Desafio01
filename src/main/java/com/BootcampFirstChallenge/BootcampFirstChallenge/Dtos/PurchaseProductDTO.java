package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos;

import lombok.Data;

@Data
public class PurchaseProductDTO {
    private int productId;
    private String name;
    private String brand;
    private int quantity;
}
