package com.tecdesoftware.market_app.domain.mapper;
import com.tecdesoftware.market_app.Controller.domain.Purchase;
import com.tecdesoftware.market_app.persistance.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
        @Mapping(source = "idCompra", target = "idCompra"),
        @Mapping(source = "idCliente", target = "idCliente"),
        @Mapping(source = "fecha", target = "fecha"),
        @Mapping(source = "medioPago", target = "medioPago"),
        @Mapping(source = "comentario", target = "comentario"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "compras", target = "items")
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
