package com.tecdesoftware.market_app.Controller;

import com.tecdesoftware.market_app.persistance.ProductRepository;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }
    
    @GetMapping("/products/category/{categoryId}")
    public List<Product> findByCategoryId(@PathVariable Integer categoryId) {
        return productRepository.findByIdCategoria(categoryId);
    }
    
    @GetMapping("/products/low-stock/{stock}/{estado}")
    public Optional<List<Product>> getEscasos(@PathVariable int stock, @PathVariable boolean estado) {
        return productRepository.getEscasos(stock, estado);
    }
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return productRepository.getProductById(id);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @GetMapping("/products/save")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}