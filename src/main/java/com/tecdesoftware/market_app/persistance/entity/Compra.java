package com.tecdesoftware.market_app.persistance.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_compra;

    @Column (name = "id_cliente")
    private String idCliente;

    private LocalDateTime fecha;


    @Column(name= "medio_pago")
    private String medioPago;

    private String comentario;

    @Column(name = "estado", columnDefinition = "CHAR(1)")
    private String estado;



    // Relación con la entidad Cliente, join column porque id cliente es una clave foránea en la tabla compras
    @ManyToOne // indica que hay una relación de muchos a uno con la entidad Cliente
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false) // nombre de la columna en la BD
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "compra",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CompraProducto> compras = new ArrayList<>();

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<CompraProducto> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraProducto> compras) {
        this.compras = (compras != null) ? compras : new ArrayList<>();
    }
}
