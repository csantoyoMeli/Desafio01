package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PurchaseProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;

import java.util.List;

public interface ProductRepository {
    public List getProducts(List<Criterion> criteriaValues, String order) throws ProductException;
    public ProductDTO getProductToPurchase(PurchaseProductDTO article) throws ProductException;
}
