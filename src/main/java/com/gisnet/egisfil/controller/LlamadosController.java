
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.domain.Mostrador;
import com.gisnet.egisfil.domain.Sucursal;
import com.gisnet.egisfil.domain.Ticket;
import com.gisnet.egisfil.domain.TurnoAtendido;
import com.gisnet.egisfil.repositoryservice.SucursalRepositoryService;
import com.gisnet.egisfil.repositoryservice.TicketRepositoryService;
import com.gisnet.egisfil.repositoryservice.TurnoAtendidoRepositoryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LlamadosController {
    @Autowired
    SucursalRepositoryService repoSuc;
    
    @Autowired
    TicketRepositoryService repo;
    
    @Autowired
    TurnoAtendidoRepositoryService repoTurno;
    
    private ObjectMapper maper = new ObjectMapper();
    
    @GetMapping("/api/llamado")
    public String siguienteTurno(@RequestParam String tipo_servicio,@RequestParam String id_sucursal, @RequestParam String clave,HttpServletRequest req) throws JsonProcessingException{
        List<Ticket> lista = repo.findByTipo_Servicio(tipo_servicio,id_sucursal);
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        if(repoSuc.findOne(id_sucursal).isEmpty()){
            return "Error en la sucursal seleccionada";
        }
        Sucursal suc = repoSuc.findOne(id_sucursal).get();
        List<Mostrador> mostradores = suc.getMostradores();
        Mostrador mostrador = null;
        for(Mostrador m : mostradores){
            if(m.getClave().compareTo(clave) == 0){
                mostrador = m;
            }
        }
        if(mostrador == null){
            return "Error en el mostrador seleccionado";
        }
        
        
        lista.sort((t1,t2)->t1.getHora_llegada().compareTo(t2.getHora_llegada()));
        Ticket actual = lista.get(0);
        String espera = calcularTiempoEspera(actual.getHora_llegada());
        actual.setTiempo_espera(espera);
        actual.setStatus(2);
        actual.setHora_inicio(System.currentTimeMillis());
        
        
        
        
        actual.setMostrador(mostrador);
        RestTemplate restT = new RestTemplate();
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        
        
        String url = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/displayback/api/call";
        HttpEntity<String> entity = new HttpEntity<>(maper.writeValueAsString(actual),h);
        ResponseEntity<?> result = restT.exchange(url,HttpMethod.POST,entity,String.class);
        repo.update(actual);
        return maper.writeValueAsString(actual);   
    }
    
    @GetMapping("/api/llamadoprioridad")
    public String porPrioridad(@RequestParam String tipo_servicio,@RequestParam String id_sucursal,@RequestParam String clave,HttpServletRequest req) throws JsonProcessingException{
        List<Ticket> lista = repo.findByTipo_Servicio(tipo_servicio,id_sucursal);
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        if(repoSuc.findOne(id_sucursal).isEmpty()){
            return "Error en la sucursal seleccionada";
        }
        Sucursal suc = repoSuc.findOne(id_sucursal).get();
        List<Mostrador> mostradores = suc.getMostradores();
        Mostrador mostrador = null;
        for(Mostrador m : mostradores){
            if(m.getClave().compareTo(clave) == 0){
                mostrador = m;
            }
        }
        if(mostrador == null){
            return "Error en el mostrador seleccionado";
        }
        lista.sort((t1,t2)-> Integer.compare(t1.getServicio().getPrioridad(), t2.getServicio().getPrioridad()));
        Ticket actual = lista.get(0);;
        for(Ticket t : lista){
            String espera = calcularTiempoEspera(t.getHora_llegada());
            t.setTiempo_espera(espera);
            long esperaMilis = System.currentTimeMillis() - t.getHora_llegada();
            int minutes = (int) ((esperaMilis / (1000*60)) % 60);
            if(minutes >= t.getServicio().getTiempo_maximo_espera()){
                actual = t;
                break;
            }
        }  
        actual.setStatus(2);
        actual.setHora_inicio(System.currentTimeMillis());
        actual.setMostrador(mostrador);
        repo.update(actual);
        
        RestTemplate restT = new RestTemplate();
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        
        
        String url = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/displayback/api/call";
        HttpEntity<String> entity = new HttpEntity<>(maper.writeValueAsString(actual),h);
        ResponseEntity<?> result = restT.exchange(url,HttpMethod.POST,entity,String.class);
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
        TurnoAtendido turno = finalizarTurno(repo.findOne(ticket.getId()).get(),4);
        repo.delete(ticket);
        return maper.writeValueAsString(repoTurno.create(turno));
    }
    
    @PostMapping("/api/abandonoturno")
    public String abandonoTurno(@RequestBody Ticket ticket) throws JsonProcessingException{
        if(repo.findOne(ticket.getId()).isEmpty()){
            return "Error el trno no existe";
        }
        TurnoAtendido turno = finalizarTurno(repo.findOne(ticket.getId()).get(),3);
        repo.delete(ticket);
        return maper.writeValueAsString(repoTurno.create(turno));
    }
    @PostMapping("/api/ponerturnoenespera")
    public String ponerTurnoEspera(@RequestBody Ticket ticket) throws JsonProcessingException{
        if(repo.findOne(ticket.getId()).isEmpty()){
            return "Error el trno no existe";
        }
        ticket = repo.findOne(ticket.getId()).get();
        ticket.setStatus(2);
        return maper.writeValueAsString(repo.update(ticket));
        
    }
    
    @GetMapping("/api/llamadoturno")
    public String LllamadoATurno(@RequestParam String tipo_servicio,@RequestParam String id_sucursal,@RequestParam String clave,@RequestParam String turno,HttpServletRequest req) throws JsonProcessingException{
        if(repoSuc.findOne(id_sucursal).isEmpty()){
            return "Error en la sucursal seleccionada";
        } 
        List<Ticket> lista = repo.findByTipo_Servicio_en_espera(tipo_servicio,id_sucursal);
        if(lista.isEmpty()){
            return maper.writeValueAsString(lista);
        }
        Sucursal suc = repoSuc.findOne(id_sucursal).get();
        List<Mostrador> mostradores = suc.getMostradores();
        Mostrador mostrador = null;
        for(Mostrador m : mostradores){
            if(m.getClave().compareTo(clave) == 0){
                mostrador = m;
            }
        }
        if(mostrador == null){
            return "Error en el mostrador seleccionado";
        }
        Ticket ticket = null;
        for(Ticket t : lista){
            if(t.getTurno().compareTo(turno) == 0){
                ticket = t;
                break;
            }
        }
        if(ticket == null){
            return "No se esncontro el turno";
        }
        return maper.writeValueAsString(ticket);
    }
    
    public TurnoAtendido finalizarTurno(Ticket ticket,int status){
        
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
        turno.setStatus(status);
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
