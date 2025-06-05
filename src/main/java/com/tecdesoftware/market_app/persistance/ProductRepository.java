package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tecdesoftware.market_app.persistance.crud.ProductCrudRepository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;  // Inyecta el CrudRepository, no a sí mismo

    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();  // Usa el CrudRepository
    }
}

