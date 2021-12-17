
package com.gisnet.egisfil.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Cliente {
    @Id
    private String id;
    
    @Field
    private String clave;
    
    @Field 
    private String numero_cliente;
    
    @Field
    private String nombre;
    
    @Field
    private String apellidos;
    
    @Field
    private Date fecha_nacimiento;
    
    @Field
    private boolean activo;

    public String getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getNumero_cliente() {
        return numero_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNumero_cliente(String numero_cliente) {
        this.numero_cliente = numero_cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
}
