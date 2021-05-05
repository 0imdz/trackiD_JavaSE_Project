/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.sql.Date;

/**
 *
 * @author Ismael
 */
public class Cancion extends Lanzamiento{
    private char c_explicito;
    private int duracion;
    
    public Cancion (int upc, String titulo, String autoria, String genero, Date fecha_lanzamiento, String sello, int usuario_id, char c_explicito, int duracion){
        super(upc, titulo, autoria, genero, fecha_lanzamiento, sello, usuario_id); //heredo de lanzamiento
        this.c_explicito=c_explicito;
        this.duracion=duracion;
    }
    
    public Cancion(){
        
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public char getC_explicito() {
        return c_explicito;
    }

    public void setC_explicito(char c_explicito) {
        this.c_explicito = c_explicito;
    }
}
