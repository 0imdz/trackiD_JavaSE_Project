/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import com.mycompany.mavenproject1.Alert_Util_1;
import java.sql.Date;

/**
 *
 * @author Ismael
 */
public class Cancion extends Lanzamiento{
    private String c_explicito;
    private int duracion;
    private String audio;
    private String imagen;
    
    public Cancion (int upc, String titulo, String autoria, String genero, Date fecha_lanzamiento, String sello, int usuario_id, String c_explicito, int duracion, String audio, String imagen){
        super(upc, titulo, autoria, genero, fecha_lanzamiento, sello, usuario_id); //heredo de lanzamiento
        this.c_explicito=c_explicito;
        this.duracion=duracion;
        this.audio=audio;
        this.imagen=imagen;
    }
    
    public Cancion(String[] importeCanciones){
        super(Integer.parseInt(importeCanciones[0]),importeCanciones[1],importeCanciones[2],importeCanciones[3],Date.valueOf(importeCanciones[4]),importeCanciones[5],Integer.parseInt(importeCanciones[6]));
        this.c_explicito = importeCanciones[7];
        this.duracion = Integer.parseInt(importeCanciones[8]);
        this.audio=importeCanciones[9];
        this.imagen=importeCanciones[10];
    }
    
    public Cancion(){
        
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getC_explicito() {
        return c_explicito;
    }

    public void setC_explicito(String c_explicito) {
        this.c_explicito = c_explicito;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    public static boolean comprobacion(String[] canciones_1){
        boolean salir=false;
        int cont=0;
        
        if(canciones_1.length == 11){
            for(int i=0; i<canciones_1.length; i++){
                if(!canciones_1[i].isBlank()){
                    cont++;
                }
            }
            if(cont==canciones_1.length){
                salir=true;
            }
        }
        else{
            Alert_Util_1.mostrarError("Asegúrate de que la longitud del fichero es correcta.");
        }
        return salir;
    }
}
