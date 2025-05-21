package com.tecdesoftware.market_app.Controller;

import jakarta.persistence.*;

@Entity //this annotation indicates that this class is an entity and is mapped to a database table
@Table(name = "productos") // indica el nombre exacto de la tabla en la base de datos
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto") // columna correspondiente en la BD
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne // indica que hay una relación de muchos a uno con la entidad Categoria
    @JoinColumn(name = "id_categoria", nullable = false) // nombre de la columna en la BD
    private Categoria categoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double price;

    @Column(name = "cantidad_stock")
    private Integer stock;

    @Column(name = "estado")
    private Boolean estado;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }


}
                    