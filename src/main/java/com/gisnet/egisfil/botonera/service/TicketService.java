
package com.gisnet.egisfil.botonera.service;


import com.gisnet.egisfil.botonera.domain.Ticket;
import java.util.List;
import java.util.Optional;


public interface TicketService {
    Optional<Ticket> findOne(String id);
    List<Ticket> findAll();
    Ticket create(Ticket ticket);
    Ticket update(Ticket ticket);
}
