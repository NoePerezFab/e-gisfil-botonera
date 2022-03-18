
package com.gisnet.egisfil.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Scope("turnos_atendidos")
@Collection("turnos_atendidos")
@Document
public class TurnoAtendido implements Serializable{
    
    @Id
    private String id;
    
    @Field
    private Servicios servicio;
    
    @Field 
    private String turno;
    
    @Field
    private long hora_llegada;
    
    @Field
    private boolean cliente;
    
    @Field 
    private String tiempo_espera;
    
    @Field 
    private String tiempo_atencion;
    
    @Field
    private Mostrador mostrador;
    
    @Field
    private String id_sucursal;
    
    @Field
    private long hora_inicio;
    
    @Field
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    

    public long getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(long hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    
    

    public String getId() {
        return id;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public String getTurno() {
        return turno;
    }

    public long getHora_llegada() {
        return hora_llegada;
    }

    public boolean isCliente() {
        return cliente;
    }

    public String getTiempo_espera() {
        return tiempo_espera;
    }

    public String getTiempo_atencion() {
        return tiempo_atencion;
    }

    public Mostrador getMostrador() {
        return mostrador;
    }

    public String getId_sucursal() {
        return id_sucursal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setHora_llegada(long hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public void setTiempo_espera(String tiempo_espera) {
        this.tiempo_espera = tiempo_espera;
    }

    public void setTiempo_atencion(String tiempo_atencion) {
        this.tiempo_atencion = tiempo_atencion;
    }

    public void setMostrador(Mostrador mostrador) {
        this.mostrador = mostrador;
    }

    public void setId_sucursal(String id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
    
    
    
    
}
