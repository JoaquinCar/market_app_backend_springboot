package com.tecdesoftware.market_app.domain.Service;

import com.tecdesoftware.market_app.Controller.domain.Purchase;
import com.tecdesoftware.market_app.domain.Repository.CompraRepository;
import com.tecdesoftware.market_app.domain.Repository.PurchaseRepository;
import com.tecdesoftware.market_app.persistance.entity.Compra;
import com.tecdesoftware.market_app.persistance.entity.CompraProducto;
import com.tecdesoftware.market_app.persistance.mapper.PurchaseMapper;
import com.tecdesoftware.market_app.persistance.mapper.PurchaseMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;


    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }



    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getPurchasesByUserId(String idCliente) {
        return purchaseRepository.getPurchasesByUserId(idCliente);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

}
