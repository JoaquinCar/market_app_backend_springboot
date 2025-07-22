package com.tecdesoftware.market_app.persistance.mapper;

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
            @Mapping(source = "id.idProducto", target = "idProducto"),
            @Mapping(source = "estado", target = "active"),
    })

    PurchaseItem toPurchaseItem(CompraProducto producto);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.idCompra", ignore = true),
            @Mapping(target = "compra", ignore = true)
    })
    CompraProducto toCompraProducto( PurchaseItem item);

    @Mappings({
            @Mapping(source = "id_compra", target = "idProducto"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(target = "cantidad", ignore = true),
            @Mapping(target = "total", ignore = true)
    })
    PurchaseItem toPurchaseItem(Compra compra);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idCliente", ignore = true),
            @Mapping(target = "fecha", ignore = true),
            @Mapping(target = "cliente", ignore = true),
    })
    Compra toCompra(PurchaseItem item);
}
