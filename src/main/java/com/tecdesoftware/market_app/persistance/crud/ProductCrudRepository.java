package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {


    List<Product> findByIdCategoria(Integer idCategoria);

    Optional<List<Product>> findByStockLessThanAndEstado(int stock, boolean estado);



}
