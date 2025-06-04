package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.ProductRepository;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {

}
