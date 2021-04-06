package com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List> getProducts(@RequestParam(required = false) String category,
                                            @RequestParam(required = false) String freeShiping,
                                            @RequestParam(required = false) String order) {
        return new ResponseEntity<>(articleService.getProducts(category, freeShiping, order), HttpStatus.OK);
    }

}
