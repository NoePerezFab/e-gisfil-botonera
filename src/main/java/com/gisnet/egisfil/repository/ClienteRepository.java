
package com.gisnet.egisfil.repository;

import com.gisnet.egisfil.domain.Cliente;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CouchbaseRepository<Cliente, String>{
    
}
