package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.ProductRepository;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {

    @RestController
    class ProductController {
        private final ProductRepository productRepository;

        public ProductController(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @GetMapping("/products")
        public List<Product> getAllProducts() {
            return productRepository.getAll();
        }
    }
}
