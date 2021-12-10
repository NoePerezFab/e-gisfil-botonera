
package com.gisnet.egisfil.botonera.service;


import com.gisnet.egisfil.botonera.domain.Cliente;
import java.util.List;
import java.util.Optional;


public interface ClienteService {
    Optional<Cliente> findOne(String id);
    List<Cliente> findAll();
    Cliente create(Cliente cliente);
    Cliente update(Cliente cliente);
}
