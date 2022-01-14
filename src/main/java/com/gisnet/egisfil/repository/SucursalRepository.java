
package com.gisnet.egisfil.repository;

import com.gisnet.egisfil.domain.Sucursal;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends CouchbaseRepository<Sucursal, String>{
    
}
