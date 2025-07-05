package com.tecdesoftware.market_app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Producto {
    @JsonProperty("id")
    private Integer productId;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("idCategoria")
    private Integer categoryId;

    @JsonProperty("codigoBarras")
    private String barcode;

    @JsonProperty("precioVenta")
    private BigDecimal price;

    @JsonProperty("cantidadStock")
    private Integer stock;

    @JsonProperty("estado")
    private Boolean active;

    @JsonProperty("categoria")
    private Category category;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}