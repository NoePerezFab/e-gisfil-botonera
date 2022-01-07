
package com.gisnet.egisfil.repositoryservice;

import com.gisnet.egisfil.botonera.service.TurnoAtendidoService;
import com.gisnet.egisfil.domain.TurnoAtendido;
import com.gisnet.egisfil.repository.TurnoAtendidoRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoAtendidoRepositoryService implements TurnoAtendidoService {
    @Autowired
    TurnoAtendidoRepository repo;
    @Override
    public Optional<TurnoAtendido> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public List<TurnoAtendido> findAll() {
        List<TurnoAtendido> lista = new ArrayList<>();
        Iterator it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add((TurnoAtendido)it.next());
        }
        return lista;
    }

    @Override
    public TurnoAtendido create(TurnoAtendido turnoAtendido) {
        return repo.save(turnoAtendido);
    }

    @Override
    public TurnoAtendido update(TurnoAtendido turnoAtendido) {
        return repo.save(turnoAtendido);
    }
    
}
