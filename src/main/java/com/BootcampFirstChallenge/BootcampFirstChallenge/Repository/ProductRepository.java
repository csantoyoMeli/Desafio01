package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;

import java.util.List;

public interface ProductRepository {
    public List getProducts(List<Criterion> criteriaValues, String order);
}
