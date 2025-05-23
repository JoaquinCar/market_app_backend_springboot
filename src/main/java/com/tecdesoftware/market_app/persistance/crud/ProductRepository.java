package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Aquí puedes agregar métodos personalizados de consulta si los necesitas
}

