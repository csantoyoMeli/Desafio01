package com.BootcampFirstChallenge.BootcampFirstChallenge.Services;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProducts(String categoryName, String freeShiping, String order);
}
