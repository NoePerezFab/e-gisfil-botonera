
package com.gisnet.egisfil.botonera.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.botonera.domain.Ticket;
import com.gisnet.egisfil.botonera.repositoryservice.TicketRepositoryService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LlamadosController {
    @Autowired
    TicketRepositoryService repo;
    
    private ObjectMapper maper = new ObjectMapper();
    
    @GetMapping("/api/llamado")
    public String siguienteTurno() throws JsonProcessingException{
        List<Ticket> lista = repo.findAll();
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        long actualTime = System.currentTimeMillis();
        lista.sort((t1,t2)->t1.getHora_llegada().compareTo(t2.getHora_llegada()));
        System.out.println(lista);

        return "";
        
        
    }
    
    
    
    
}
