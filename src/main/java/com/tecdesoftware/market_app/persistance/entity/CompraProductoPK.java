package com.tecdesoftware.market_app.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable // Esta anotación indica que esta clase es una clave primaria compuesta
public class CompraProductoPK {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;


    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
