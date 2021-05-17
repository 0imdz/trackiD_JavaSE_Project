/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public class Usuario {
    private int idusuario;
    private String nombre_usuario;
    private String password;
    private String pregunta;
    private String respuesta;
    
    public Usuario(){
        
    }

    public Usuario(int idusuario, String nombre_usuario, String password, String pregunta, String respuesta) {
        this.idusuario = idusuario;
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    //nombre de usuario mayor o igual a seis
    public boolean checkNombre(String nombre_usuario){
       return nombre_usuario.length() >= 6;
    }
    
//    //email 
//    public boolean checkEmail(String correo){
//        //return email.matches("@+");
//        boolean ok = false;
//        if (correo.matches("[-\\w\\.]+@\\w+\\.\\w+")) 
//            ok = true;
//        return ok;
//    }
    
    //password mayor o igual a ocho
    public boolean checkPassword(String password){
        return password.matches(".*[A-Z].*") && password.length() >= 8;
    }
}