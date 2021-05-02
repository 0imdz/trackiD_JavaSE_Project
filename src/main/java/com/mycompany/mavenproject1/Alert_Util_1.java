/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import javafx.scene.control.Alert;

/**
 *
 * @author Ismael
 */
public class Alert_Util_1 {
    public static void mostrarError(String mensaje){
        Alert alerta=new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    
    public static void mostrarInfo(String mensaje){
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}
