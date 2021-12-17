
package com.gisnet.egisfil.repositoryservice;

import com.gisnet.egisfil.domain.Cliente;
import com.gisnet.egisfil.repository.ClienteRepository;
import com.gisnet.egisfil.botonera.service.ClienteService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteRepositoryService implements ClienteService{
    @Autowired
    ClienteRepository repo;
    @Override
    public Optional<Cliente> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        Iterator it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add((Cliente)it.next());
        }
        return lista;
    }

    @Override
    public Cliente create(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
       return repo.save(cliente);
    }
    
}
