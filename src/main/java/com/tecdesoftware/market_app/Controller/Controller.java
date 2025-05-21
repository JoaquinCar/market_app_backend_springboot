package com.tecdesoftware.market_app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class Controller {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/GET")
    public List<Product> hello() {
        return productRepository.findAll();
    }
}


