package com.tecdesoftware.market_app.domain.mapper;

import com.tecdesoftware.market_app.Controller.domain.PurchaseItem;
import com.tecdesoftware.market_app.persistance.entity.Compra;
import com.tecdesoftware.market_app.persistance.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "idCompra", target = "idProducto"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "active", target = "estado"),

    })

    PurchaseItem toPurchaseItem(CompraProducto producto);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.compra", ignore = true),
            @Mapping(target = "compra", ignore = true)
    })
    CompraProducto toCompraProducto( PurchaseItem item);
}
