package com.tecdesoftware.market_app.web.controller;

import com.tecdesoftware.market_app.domain.Service.ProductService;
import com.tecdesoftware.market_app.domain.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/market_app")
@Tag(name = "Product Controller", description = "Manages product in the store")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Retrieves a list of all products available in the store")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public List<Producto> getAllProducts() {
        return productService. getAllProducts();
    }
    
    @GetMapping("/products/category/{categoryId}")
    @Operation(summary = "Get product by category ID", description = " Retrieves a list of products by category ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products by category ID")
    @ApiResponse(responseCode = "404", description = "Internal server error")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Optional<List<Producto>> findByCategoryId(@Parameter(description = "ID of the product to be retrieved", example = "/",required = true) @PathVariable Integer categoryId) {
        return productService.findByIdCategoria(categoryId);
    }
    
    @GetMapping("/products/low-stock/{stock}/{estado}")
    @Operation(summary = "Get products with low stock", description = "Retrieves a list of products with stock below a specified level")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products with low stock")
    @ApiResponse(responseCode = "404", description = "No products found with low stock")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Optional<List<Producto>> getEscasos(@PathVariable int stock, @PathVariable boolean estado) {
        return productService.getEscasos(stock, estado);
    }
    @GetMapping("/products/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of product by ID")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Optional<Producto> getProductById(@Parameter(description = "id of the product required",example = "/",required = true)@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/delete/{id}")
    @Operation(summary = "Delete product by ID", description = "Deletes a product by its ID")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "400", description = "Invalid ID provided")
    @ApiResponse(responseCode = "401", description = " Unauthorized access")
    @ApiResponse(responseCode = "403", description = " Forbidden access")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Map<String, String>> deleteProduct(@Parameter(description = "ID to delete a product with ID", example = "/", required = true)@PathVariable Integer id) {
        productService.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado correctamente");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/products/save")
    @Operation(summary = "Save a new product", description = "Saves a new product in the store",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    examples = @ExampleObject(
                            name = "Product Example",
                            value = """
                                    {
                                    "name" = "Example Product",
                                    "categoryId" = 2,
                                    "price" = "19.99",
                                    "stock" = 100,
                                    "active" = true,
                                    }
                                    """
                    )
            )
    )
    )
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized access")
    @ApiResponse(responseCode = "403", description = "Forbidden access")
    @ApiResponse(responseCode = "409", description = " Conflict, product already exists")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Producto saveProduct(@RequestBody Producto producto) {
        return productService.save(producto);
    }

    
}