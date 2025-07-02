package com.tecdesoftware.market_app.persistance;

import com.tecdesoftware.market_app.persistance.entity.Categoria;

import java.math.BigDecimal;

public class Producto {
    private Integer idProducto;
    private String nombre;
    private Integer idCategory;
    private String codeBarra;
    private BigDecimal precio;
    private Integer cantidadStock;
    private Boolean state;
    private Categoria categoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getCodeBarra() {
        return codeBarra;
    }

    public void setCodeBarra(String codeBarra) {
        this.codeBarra = codeBarra;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
