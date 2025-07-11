package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.Controller.domain.Purchase;
import com.tecdesoftware.market_app.Controller.domain.PurchaseRepository;
import com.tecdesoftware.market_app.domain.mapper.PurchaseMapper;
import com.tecdesoftware.market_app.persistance.crud.CompraCRUDRepository;
import com.tecdesoftware.market_app.persistance.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {


    @Autowired
    private CompraCRUDRepository compraCRUDRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;


    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCRUDRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getPurchasesByUserId(String idCliente) {
        return compraCRUDRepository.findByIdCliente(idCliente)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compraEntity = purchaseMapper.toCompra(purchase);
        compraEntity.getCompras().forEach(producto -> producto.setCompra(compraEntity));
        return purchaseMapper.toPurchase(compraCRUDRepository.save(compraEntity));
    }
}
