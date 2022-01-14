
package com.gisnet.egisfil.domain;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Sucursal implements Serializable{
    
    @Id
    private String id;
    
    @Field
    private String clave;
    
    @Field
    private String nombre;
    
    @Field
    private Direccion direccion;
    
    @Field
    private List<String> telefono;
    
    @Field
    private int ind_llamado;
    
    @Field
    private List<Mostrador> mostradores;
    
    @Field
    private String type;
    
    @Field
    private List<Servicios> servicios;

    public List<Servicios> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicios> servicios) {
        this.servicios = servicios;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public List<String> getTelefono() {
        return telefono;
    }

    public int getInd_llamado() {
        return ind_llamado;
    }

    public List<Mostrador> getMostradores() {
        return mostradores;
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

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(List<String> telefono) {
        this.telefono = telefono;
    }

    public void setInd_llamado(int ind_llamado) {
        this.ind_llamado = ind_llamado;
    }

    public void setMostradores(List<Mostrador> mostradores) {
        this.mostradores = mostradores;
    }
    
    
    
    
    
}
