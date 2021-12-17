
package com.gisnet.egisfil.repository;


import com.gisnet.egisfil.domain.Ticket;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CouchbaseRepository<Ticket, String>{
    
}
