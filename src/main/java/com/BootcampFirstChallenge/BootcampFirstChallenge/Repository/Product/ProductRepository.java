package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.PurchaseProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;

import java.util.List;

public interface ProductRepository {
    public List getProducts(List<Criterion> criteriaValues, String order) throws ProductException;
    public ProductDTO getProductToPurchase(PurchaseProductDTO article) throws ProductException;
}
