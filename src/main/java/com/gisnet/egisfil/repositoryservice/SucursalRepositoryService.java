
package com.gisnet.egisfil.repositoryservice;

import com.gisnet.egisfil.botonera.service.SucursalService;
import com.gisnet.egisfil.domain.Sucursal;
import com.gisnet.egisfil.repository.SucursalRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalRepositoryService implements SucursalService{
    @Autowired
    SucursalRepository repo;
    @Override
    public Optional<Sucursal> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Sucursal> findAll() {
        List<Sucursal> lista = new ArrayList<>();
        Iterator it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add((Sucursal)it.next());
        }
        return lista;
    }

    @Override
    public Sucursal create(Sucursal sucursal) {
        return repo.save(sucursal);
    }

    @Override
    public Sucursal update(Sucursal sucursal) {
        return repo.save(sucursal);
    }
    
}
