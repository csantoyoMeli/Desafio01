package com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.PayloadDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/purchase-request")
    public ResponseEntity purchaseRequest(@RequestBody PayloadDTO payload) throws ProductException {
        return new ResponseEntity<>(articleService.purchaseRequest(payload), HttpStatus.OK);
    }

    @GetMapping("/market-car")
    public ResponseEntity getTotalPurchase() {
        return new ResponseEntity<>(articleService.getMarketCar(), HttpStatus.OK);
    }
}
