package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.domain.Producto;
import com.tecdesoftware.market_app.domain.mapper.ProductMapper;
import com.tecdesoftware.market_app.domain.mapper.ProductoRepository;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tecdesoftware.market_app.persistance.crud.ProductCrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductoRepository {
    //inyectado automáticamente por Spring
    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Producto> getAll() {
         List<Product> productos = (List<Product>) productCrudRepository.findAll();
         return productMapper.toProducts(productos);
    }

    public Optional<List<Producto>> findByIdCategoria(Integer idCategoria) {
        List<Product> productos= productCrudRepository.findByIdCategoria(idCategoria);
        return Optional.of(productMapper.toProducts(productos));
    }


    public Optional<List<Producto>> getEscasos(int stock, boolean estado) {
        Optional<List<Product>> productos =productCrudRepository.findByStockLessThanAndEstado(stock, estado);
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    @Override
    public Optional<Producto> getProductById(Integer id) {
        return productCrudRepository.findById(id)
                .map(product -> productMapper.toProduct(product));
    }
    public void delete(Integer id) {
        productCrudRepository.deleteById(id);
        System.out.println("Producto con ID " + id + " eliminado.");
    }

    @Override
    public Producto save(Producto producto) {
        Product productEntity = productMapper.toEntityProduct(producto);
        Product savedEntity = productCrudRepository.save(productEntity);
        return productMapper.toProduct(savedEntity);
    }

}
