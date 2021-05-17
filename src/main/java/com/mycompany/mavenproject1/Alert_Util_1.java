/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

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
    
    public static void mostrarConfirmacion(String mensaje){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmar cambios");
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
        Optional<ButtonType> action = alert.showAndWait();
    }
    
    public static void mostrarConfirmacionEliminacion(String mensaje){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Eliminado");
        alert.setTitle("Confirmación de eliminación");
        alert.setContentText("¿Estas seguro de eliminar?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
                return;
    }
}
