package com.tecdesoftware.market_app.Controller.domain;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();

    Optional<List<Purchase>> getPurchasesByUserId(Integer idCliente);

    Purchase save(Purchase purchase);
}
