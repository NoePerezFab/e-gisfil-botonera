
package com.gisnet.egisfil.repository;

import com.gisnet.egisfil.domain.TurnoAtendido;
import org.springframework.data.couchbase.repository.CouchbaseRepository;


public interface TurnoAtendidoRepository extends CouchbaseRepository<TurnoAtendido, String> {
    
    
}
