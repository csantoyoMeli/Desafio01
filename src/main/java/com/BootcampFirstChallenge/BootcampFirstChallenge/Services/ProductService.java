package com.BootcampFirstChallenge.BootcampFirstChallenge.Services;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<ProductDTO> getProducts(Map<String, String> allParams) throws ProductException;
}
