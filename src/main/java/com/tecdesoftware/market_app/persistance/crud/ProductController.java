package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.entity.Product;
import com.tecdesoftware.market_app.persistance.crud.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
