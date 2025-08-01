package com.tecdesoftware.market_app.persistance.crud;

import com.tecdesoftware.market_app.persistance.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteCrudRepository extends CrudRepository <Cliente, String> {


    // No need to define methods here, as CrudRepository provides basic CRUD operations
    // You can add custom query methods if needed, for example,
    // List<Cliente> findByNombre(String nombre);
    // List<Cliente> findByCorreoElectronico(String correoElectronico);


    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}
