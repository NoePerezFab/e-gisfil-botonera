
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.domain.Ticket;
import com.gisnet.egisfil.repositoryservice.TicketRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LlamadosController {
    @Autowired
    TicketRepositoryService repo;
    
    private ObjectMapper maper = new ObjectMapper();
    
    @GetMapping("/api/llamado")
    public String siguienteTurno(@RequestParam String tipo_servicio) throws JsonProcessingException{
        List<Ticket> lista = repo.findAll();
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        lista.sort((t1,t2)->t1.getHora_llegada().compareTo(t2.getHora_llegada()));
        Ticket actual = lista.get(0);
        String espera = calcularTiempoEspera(actual.getHora_llegada());
        actual.setTiempo_espera(espera);
        repo.update(actual);
        return maper.writeValueAsString(actual);
        
        
    }
    
    @GetMapping("/api/ponderacion")
    public String porPonderacion(@RequestParam String tipo_servicio) throws JsonProcessingException{
        List<Ticket> lista = repo.findAll();
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        
        return "";
    }
    
    public String calcularTiempoEspera(long llegada){
        long esperaMilis = System.currentTimeMillis() - llegada;
        int seconds = (int) (esperaMilis / 1000) % 60 ;
        int minutes = (int) ((esperaMilis / (1000*60)) % 60);
        int hours   = (int) ((esperaMilis / (1000*60*60)) % 24);
        String es = String.format("%01d:%02d:%02d",hours, minutes, seconds);
        return String.valueOf(es);
    }
    
    
    
    
}
