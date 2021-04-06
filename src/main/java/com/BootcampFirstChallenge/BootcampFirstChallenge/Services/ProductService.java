package com.BootcampFirstChallenge.BootcampFirstChallenge.Services;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProducts(List<Criterion>criteriaValues, String order);
}
