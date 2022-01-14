
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.domain.Sucursal;
import com.gisnet.egisfil.repositoryservice.SucursalRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SucursalController {
    @Autowired
    SucursalRepositoryService repo;

    
    private ObjectMapper maper = new ObjectMapper();
    
    @GetMapping("/api/sucursales")
    public String getSucursales() throws JsonProcessingException{
        List<Sucursal> lista = repo.findAll();
        return maper.writeValueAsString(lista);
    }
    
    
    
    
}
