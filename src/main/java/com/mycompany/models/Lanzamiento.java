/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Ismael
 */
public abstract class Lanzamiento {
    private int upc;
    private String titulo;
    private String autoria;
    private String genero;
    private Date fecha_lanzamiento;
    private String sello;
    private int usuario_id;

    public Lanzamiento(int upc, String titulo, String autoria, String genero, Date fecha_lanzamiento, String sello, int usuario_id) {
        this.upc = upc;
        this.titulo = titulo;
        this.autoria = autoria;
        this.genero = genero;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.sello = sello;
        this.usuario_id = usuario_id;
    }
    
    public Lanzamiento() {
        
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutoria() {
        return autoria;
    }

    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(Date fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }
    
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public String toString() {
        return 
               "TÍTULO: " + titulo + 
               "   ||   AUTORÍA: " + autoria + 
               "   ||   GÉNERO: " + genero;
//               " | FECHA DE LANZAMIENTO: " + fecha_lanzamiento + 
//               " | NÚMERO DE PISTAS: " + n_pistas + 
//               " | SELLO: " + sello + 
//               " | CONTENIDO EXPLÍCITO: " + c_explicito;
    }
    
    
    
}
