package com.tecdesoftware.market_app.persistance.entity;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;


    @OneToMany(mappedBy = "categoria") //en mappedBy se indica el nombre del atributo en la entidad Product que hace referencia a esta entidad
    private List<Product> productos;


    // Getters y Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}