package com.BootcampFirstChallenge.BootcampFirstChallenge.Services;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.PayloadDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.PayloadResponseDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<ProductDTO> getProducts(Map<String, String> allParams) throws ProductException;
    public PayloadResponseDTO purchaseRequest(PayloadDTO payload) throws ProductException;
}
