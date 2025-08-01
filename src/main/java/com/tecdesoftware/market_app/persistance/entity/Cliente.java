package com.tecdesoftware.market_app.persistance.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private String id;  //no es autogenerado, se usa el número de cédula

    private String nombre;

    private String apellidos;

    private BigDecimal celular; //se usa Long para evitar problemas con el formato de número de teléfono

    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;


    //usamos cliente para que la relación sea bidireccional, es decir, desde Compra podemos acceder a Cliente
    @OneToMany(mappedBy = "cliente") //mappedBy indica que esta entidad es la dueña de la relación
    @JsonManagedReference
    private List<Compra> compras;

    private String contrasena;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getCelular() {
        return celular;
    }

    public void setCelular(BigDecimal celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
