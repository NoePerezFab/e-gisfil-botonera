
package com.gisnet.egisfil.repository;


import com.gisnet.egisfil.domain.Ticket;
import java.util.List;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CouchbaseRepository<Ticket, String>{
    
    @Query("#{#n1ql.selectEntity} WHERE  `tipo_servicio` = '#{[0]}' AND `status` = 1 AND #{#n1ql.filter} ")
    public List<Ticket> findByTipo_Servicio(String tipo_servicio);
}
