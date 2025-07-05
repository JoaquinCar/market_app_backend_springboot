package com.tecdesoftware.market_app.domain.mapper;

import com.tecdesoftware.market_app.domain.Producto;
import com.tecdesoftware.market_app.persistance.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
        @Mapping(source = "id", target = "productId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "idCategoria", target = "categoryId"),
        @Mapping(source = "codigoBarras", target = "barcode"),
        @Mapping(source = "price", target = "price"),
        @Mapping(source = "stock", target = "stock"),
        @Mapping(source = "estado", target = "active"),
        @Mapping(source = "categoria", target = "category")
    })
    Producto toProduct(com.tecdesoftware.market_app.persistance.entity.Product product);
    List<Producto> toProducts(List<com.tecdesoftware.market_app.persistance.entity.Product> products);
    Product toProductEntity(Producto producto);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    com.tecdesoftware.market_app.persistance.entity.Product toEntityProduct(Producto producto);
}
