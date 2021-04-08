package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product;

import lombok.Data;

@Data
public class PurchaseProductDTO {
    private int productId;
    private String name;
    private String brand;
    private int quantity;
}
