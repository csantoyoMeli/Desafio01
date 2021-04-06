package com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List> getProducts(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) String category,
                                            @RequestParam(required = false) String freeShipping,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String price,
                                            @RequestParam(required = false) String prestige,
                                            @RequestParam(required = false) String order) throws ProductException {
        // Getting just 2 criteria
        List<Criterion> criteria = new ArrayList<>();
        int criteriaCounter = 0;

        if (name != null) {
            criteria.add(new Criterion(name, "name"));
            criteriaCounter++;
        }

        if (category != null) {
            criteria.add(new Criterion(category, "category"));
            criteriaCounter++;
        }

        if (freeShipping != null) {
            criteria.add(new Criterion(freeShipping, "freeShipping"));
            criteriaCounter++;
        }

        if (brand != null) {
            criteria.add(new Criterion(brand, "brand"));
            criteriaCounter++;
        }

        if (price != null) {
            criteria.add(new Criterion(price, "price"));
            criteriaCounter++;
        }

        if (prestige != null) {
            criteria.add(new Criterion(prestige, "prestige"));
            criteriaCounter++;
        }

        // Params Excess exception
        if (criteriaCounter > 2) {
            throw new ProductException(ProductException.PARAMS_EXCESS, ProductException.PARAMS_EXCESS_MSG);
        }

        return new ResponseEntity<>(articleService.getProducts(criteria, order), HttpStatus.OK);
    }
}
