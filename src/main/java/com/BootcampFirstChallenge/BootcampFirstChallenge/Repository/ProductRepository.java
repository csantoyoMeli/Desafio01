package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository;

import java.util.List;

public interface ProductRepository {
    public List getProducts(String categoryName, String freeShiping, String order);
}
