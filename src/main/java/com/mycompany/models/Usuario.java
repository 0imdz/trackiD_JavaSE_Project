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
    
    /**
     *
     */
    public Usuario(){
        
    }

    /**
     *
     * @param idusuario
     * @param nombre_usuario
     * @param password
     * @param pregunta
     * @param respuesta
     */
    public Usuario(int idusuario, String nombre_usuario, String password, String pregunta, String respuesta) {
        this.idusuario = idusuario;
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     *
     * @return
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     *
     * @param idusuario
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    /**
     *
     * @return
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     *
     * @param nombre_usuario
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     *
     * @return
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     *
     * @param pregunta
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    /**
     *
     * @return
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     *
     * @param respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Método que checkea la correcta introducción de un nombre de usuario válido
     * @param nombre_usuario
     * @return
     */
    public boolean checkNombre(String nombre_usuario){
       return nombre_usuario.length() >= 6;
    }

    /**
     * Método que checkea la correcta introducción de un nombre de usuario válido
     * @param password
     * @return
     */
    public boolean checkPassword(String password){
        return password.matches(".*[A-Z].*") && password.length() >= 8;
    }
}