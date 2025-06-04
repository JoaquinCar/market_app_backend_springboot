package com.tecdesoftware.market_app.persistance.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private String id;  //no es autogenerado, se usa el número de cédula

    private String nombre;

    private String apellidos;

    private Long celular; //se usa Long para evitar problemas con el formato de número de teléfono

    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;


    //usamos cliente para que la relación sea bidireccional, es decir, desde Compra podemos acceder a Cliente
    @OneToMany(mappedBy = "cliente") //mappedBy indica que esta entidad es la dueña de la relación
    private List<Compra> compras;


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

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
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
}
