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
public class Cancion {
    private int upc;
    private String titulo;
    private String autoria;
    private String genero;
    private String formato;
    private Date fecha_lanzamiento;
    private int n_pistas;
    private String sello;
    private char c_explicito;
    private int usuario_id;

    public Cancion(int upc, String titulo, String autoria, String genero, String formato, Date fecha_lanzamiento, int n_pistas, String sello, char c_explicito, int usuario_id) {
        this.upc = upc;
        this.titulo = titulo;
        this.autoria = autoria;
        this.genero = genero;
        this.formato = formato;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.n_pistas = n_pistas;
        this.sello = sello;
        this.c_explicito = c_explicito;
        this.usuario_id = usuario_id;
    }
    
    public Cancion() {
        
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Date getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(Date fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public int getN_pistas() {
        return n_pistas;
    }

    public void setN_pistas(int n_pistas) {
        this.n_pistas = n_pistas;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public char getC_explicito() {
        return c_explicito;
    }

    public void setC_explicito(char c_explicito) {
        this.c_explicito = c_explicito;
    }
    
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public String toString() {
        return "UPC: " + upc + 
               " | TÍTULO: " + titulo + 
               " | AUTORÍA: " + autoria + 
               " | GÉNERO: " + genero + 
               " | FORMATO: " + formato;
//               " | FECHA DE LANZAMIENTO: " + fecha_lanzamiento + 
//               " | NÚMERO DE PISTAS: " + n_pistas + 
//               " | SELLO: " + sello + 
//               " | CONTENIDO EXPLÍCITO: " + c_explicito;
    }
    
    
    
}
