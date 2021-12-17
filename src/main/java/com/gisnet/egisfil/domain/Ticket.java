
package com.gisnet.egisfil.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Ticket {
    @Id
    private String id;
   
    @Field
    private Long hora_llegada;
    
    @Field
    private Long tiempo_espera;
    
    @Field
    private String type;
    
    @Field
    private Cliente cliente;
    
    @Field
    private Servicios servicio;

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }
    
    

    public String getId() {
        return id;
    }

    public Long getHora_llegada() {
        return hora_llegada;
    }

    public Long getTiempo_espera() {
        return tiempo_espera;
    }

    public String getType() {
        return type;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHora_llegada(Long hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public void setTiempo_espera(Long tiempo_espera) {
        this.tiempo_espera = tiempo_espera;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
