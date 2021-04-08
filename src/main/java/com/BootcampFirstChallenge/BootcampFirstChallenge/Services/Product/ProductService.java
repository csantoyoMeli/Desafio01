package com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.MarketCarDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PayloadDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PayloadResponseDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<ProductDTO> getProducts(Map<String, String> allParams) throws ProductException;
    public PayloadResponseDTO purchaseRequest(PayloadDTO payload) throws ProductException;
    public MarketCarDTO getMarketCar();
}
