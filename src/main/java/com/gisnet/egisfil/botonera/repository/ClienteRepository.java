
package com.gisnet.egisfil.botonera.repository;

import com.gisnet.egisfil.botonera.domain.Cliente;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CouchbaseRepository<Cliente, String>{
    
}
