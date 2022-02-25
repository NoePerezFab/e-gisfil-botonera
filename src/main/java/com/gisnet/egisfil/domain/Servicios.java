
package com.gisnet.egisfil.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Scope("configuracion")
@Collection("servicios")
@Document
public class Servicios implements Serializable{
    @Id
    private String id;
    
    @Field
    private String clave;
    
    @Field
    private String nombre;
    
    @Field
    private int prioridad;
    
    @Field
    private String tipo_servicio;
    
    @Field
    private boolean servicio_cliente;
    
    @Field
    private int ponderacion;
    
    @Field
    private int tiempo_maximo_espera;
    
    @Field
    private String type;

    public int getPonderacion() {
        return ponderacion;
    }

    public int getTiempo_maximo_espera() {
        return tiempo_maximo_espera;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public void setTiempo_maximo_espera(int tiempo_maximo_espera) {
        this.tiempo_maximo_espera = tiempo_maximo_espera;
    }
    
    

    public boolean isServicio_cliente() {
        return servicio_cliente;
    }

    public void setServicio_cliente(boolean servicio_cliente) {
        this.servicio_cliente = servicio_cliente;
    }
    
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public String getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }
    
   
    
    
}
