package com.tecdesoftware.market_app.domain.Service;

import com.tecdesoftware.market_app.domain.Producto;
import com.tecdesoftware.market_app.persistance.mapper.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductoRepository productRepository;

    public ProductService(ProductoRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Producto> getAllProducts() {
        return productRepository.getAll();
    }

    public Optional<List<Producto>> findByIdCategoria(Integer idCategoria) {
        return productRepository.findByIdCategoria(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int stock, boolean estado){
        return productRepository.getEscasos(stock, estado);
    }
    public Optional<Producto> getProductById(Integer id) {
        return productRepository.getProductById(id);
    }
    public void delete(Integer id) {
        productRepository.delete(id);
    }
    public Producto save(Producto producto) {
        return productRepository.save(producto);
    }
}
