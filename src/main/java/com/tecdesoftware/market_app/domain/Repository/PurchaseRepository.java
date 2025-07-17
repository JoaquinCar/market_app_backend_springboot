package com.tecdesoftware.market_app.domain.Repository;

import com.tecdesoftware.market_app.Controller.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();

    Optional<List<Purchase>> getPurchasesByUserId(String idCliente);

    Purchase save(Purchase purchase);
}
