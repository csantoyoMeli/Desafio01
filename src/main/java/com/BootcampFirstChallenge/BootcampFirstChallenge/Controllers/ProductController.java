package com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<List> getProducts(@RequestParam(required = false) Map<String, String> allParams) throws ProductException {
        return new ResponseEntity<>(articleService.getProducts(allParams), HttpStatus.OK);
    }
}
