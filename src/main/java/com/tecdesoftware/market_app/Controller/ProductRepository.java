package com.tecdesoftware.market_app.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdesoftware.market_app.Controller.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
