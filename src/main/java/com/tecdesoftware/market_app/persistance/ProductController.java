package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/products")
    public List<Product> getAll() {

        return productRepository.getAll();
    }


}
