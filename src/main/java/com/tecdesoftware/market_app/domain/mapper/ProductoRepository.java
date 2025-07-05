package com.tecdesoftware.market_app.domain.mapper;

import com.tecdesoftware.market_app.domain.Producto;
import com.tecdesoftware.market_app.persistance.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
    List<Producto> getAll();
    Optional<List<Producto>> findByIdCategoria(Integer idCategoria);
    Optional<List<Producto>> getEscasos(int stock, boolean estado);
    Optional<Producto> getProductById(Integer id);
    void delete(Integer id);
    Producto save(Producto producto);

}
