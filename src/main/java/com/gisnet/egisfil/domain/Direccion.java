
package com.gisnet.egisfil.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Direccion implements Serializable{
    
    @Id
    private String id;
    
    @Field
    private String clave;
    
    @Field
    private String calle;
    
    @Field 
    private String num_ext;
    
    @Field
    private String num_interior;
    
    @Field
    private String colonia;
    
    @Field 
    private String estado;
    
    @Field
    private String municipio;
    
    @Field
    private String cp;
    
    @Field
    private String url;
    
    @Field 
    private String contctos;

    public String getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getCalle() {
        return calle;
    }

    public String getNum_ext() {
        return num_ext;
    }

    public String getNum_interior() {
        return num_interior;
    }

    public String getColonia() {
        return colonia;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getCp() {
        return cp;
    }

    public String getUrl() {
        return url;
    }

    public String getContctos() {
        return contctos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNum_ext(String num_ext) {
        this.num_ext = num_ext;
    }

    public void setNum_interior(String num_interior) {
        this.num_interior = num_interior;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContctos(String contctos) {
        this.contctos = contctos;
    }
    
    
}
