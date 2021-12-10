
package com.gisnet.egisfil.botonera.repository;


import com.gisnet.egisfil.botonera.domain.Ticket;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CouchbaseRepository<Ticket, String>{
    
}
