package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tecdesoftware.market_app.persistance.crud.ProductCrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();
    }

    public List<Product> findByCategoryId(Integer categoryId) {
        return productCrudRepository.findByIdCategoriaOrderByNameAsc(categoryId);
    }

    public Optional<List<Product>> findByCantidadStockLessThanAndEstado(int stock, boolean estado) {
        return productCrudRepository.findByStockLessThanAndEstado(stock, estado);
    }

    public void delete(Product product) {
        productCrudRepository.delete(product);
    }
    public Product save(Product product) {
        return productCrudRepository.save(product);
    }

}
