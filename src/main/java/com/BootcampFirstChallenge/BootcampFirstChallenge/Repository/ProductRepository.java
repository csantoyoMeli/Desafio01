package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;

import java.util.List;

public interface ProductRepository {
    public List getProducts(List<Criterion> criteriaValues, String order) throws ProductException;
}
