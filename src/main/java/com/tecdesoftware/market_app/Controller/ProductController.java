package com.tecdesoftware.market_app.Controller;

import com.tecdesoftware.market_app.domain.ProductService;
import com.tecdesoftware.market_app.domain.Producto;
import com.tecdesoftware.market_app.domain.mapper.ProductoRepository;
import com.tecdesoftware.market_app.persistance.ProductRepository;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Producto> getAllProducts() {
        return productService. getAllProducts();
    }
    
    @GetMapping("/products/category/{categoryId}")
    public Optional<List<Producto>> findByCategoryId(@PathVariable Integer categoryId) {
        return productService.findByIdCategoria(categoryId);
    }
    
    @GetMapping("/products/low-stock/{stock}/{estado}")
    public Optional<List<Producto>> getEscasos(@PathVariable int stock, @PathVariable boolean estado) {
        return productService.getEscasos(stock, estado);
    }
    @GetMapping("/products/{id}")
    public Optional<Producto> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado correctamente");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/products/save")
    public Producto saveProduct(@RequestBody Producto producto) {
        return productService.save(producto);
    }

    
}