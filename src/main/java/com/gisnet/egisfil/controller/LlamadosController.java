
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.domain.Ticket;
import com.gisnet.egisfil.domain.TurnoAtendido;
import com.gisnet.egisfil.repositoryservice.TicketRepositoryService;
import com.gisnet.egisfil.repositoryservice.TurnoAtendidoRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LlamadosController {
    @Autowired
    TicketRepositoryService repo;
    
    @Autowired
    TurnoAtendidoRepositoryService repoTurno;
    
    private ObjectMapper maper = new ObjectMapper();
    
    @GetMapping("/api/llamado")
    public String siguienteTurno(@RequestParam String tipo_servicio,@RequestParam String id_sucursal) throws JsonProcessingException{
        List<Ticket> lista = repo.findByTipo_Servicio(tipo_servicio,id_sucursal);
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        lista.sort((t1,t2)->t1.getHora_llegada().compareTo(t2.getHora_llegada()));
        Ticket actual = lista.get(0);
        String espera = calcularTiempoEspera(actual.getHora_llegada());
        actual.setTiempo_espera(espera);
        actual.setStatus(2);
        actual.setHora_inicio(System.currentTimeMillis());
        repo.update(actual);
        return maper.writeValueAsString(actual);   
    }
    
    @GetMapping("/api/llamadoprioridad")
    public String porPrioridad(@RequestParam String tipo_servicio,@RequestParam String id_sucursal) throws JsonProcessingException{
        List<Ticket> lista = repo.findByTipo_Servicio(tipo_servicio,id_sucursal);
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        lista.sort((t1,t2)-> Integer.compare(t1.getServicio().getPrioridad(), t2.getServicio().getPrioridad()));
        Ticket actual = lista.get(0);
        String espera = calcularTiempoEspera(actual.getHora_llegada());
        actual.setTiempo_espera(espera);
        actual.setStatus(2);
        actual.setHora_inicio(System.currentTimeMillis());
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
    
    @PostMapping("/api/finalizarturno")
    public String finalizarTicket(@RequestBody Ticket ticket) throws JsonProcessingException{
        if(repo.findOne(ticket.getId()).isEmpty()){
            return "Error el trno no existe";
        }
        TurnoAtendido turno = finalizarTurno(repo.findOne(ticket.getId()).get());
        repo.delete(ticket);
        return maper.writeValueAsString(repoTurno.create(turno));
    }
    
    
    public TurnoAtendido finalizarTurno(Ticket ticket){
        
        TurnoAtendido turno = new TurnoAtendido();
        turno.setId(ticket.getId());
        turno.setServicio(ticket.getServicio());
        turno.setHora_llegada(ticket.getHora_inicio());
        turno.setCliente(ticket.getServicio().isServicio_cliente());
        turno.setTiempo_espera(ticket.getTiempo_espera());
        turno.setHora_inicio(ticket.getHora_inicio());
        turno.setTiempo_atencion(calcularTiempoEspera(ticket.getHora_inicio()));
        turno.setId_sucursal(ticket.getId_sucursal());
        turno.setTurno(ticket.getTurno());
        return turno;
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
