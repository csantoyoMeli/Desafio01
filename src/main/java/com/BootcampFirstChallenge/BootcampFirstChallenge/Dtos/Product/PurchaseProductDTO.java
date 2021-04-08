package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseProductDTO {
    private int productId;
    private String name;
    private String brand;
    private int quantity;
}
