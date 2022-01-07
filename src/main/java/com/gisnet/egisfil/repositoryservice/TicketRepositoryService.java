
package com.gisnet.egisfil.repositoryservice;

import com.gisnet.egisfil.domain.Ticket;
import com.gisnet.egisfil.repository.TicketRepository;
import com.gisnet.egisfil.botonera.service.TicketService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketRepositoryService implements TicketService{
    @Autowired
    TicketRepository repo;
    @Override
    public Optional<Ticket> findOne(String id) {
       return repo.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> lista = new ArrayList<>();
        Iterator it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add((Ticket)it.next());
        }
        return lista;
    }

    @Override
    public Ticket create(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return repo.save(ticket);
    }

    @Override
    public List<Ticket> findByTipo_Servicio(String tipo_servicio,String id_sucursal) {
       List<Ticket> lista = new ArrayList<>();
       Iterator it = repo.findByTipo_Servicio(tipo_servicio,id_sucursal).iterator();
       while(it.hasNext()){
           lista.add((Ticket)it.next());
       }
       return lista;
    }

    @Override
    public void delete(Ticket ticket) {
        repo.delete(ticket);
    }
    
}
