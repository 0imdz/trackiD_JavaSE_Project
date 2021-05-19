/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.UsuarioDao;
import com.mycompany.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class | Controller ventana registro
 *
 * @author Ismael
 */
public class RegisterController {
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private PasswordField txtPassword2;
    
    @FXML
    private TextField txtPregunta;
    
    @FXML
    private TextField txtRespuesta;
    
    
    
    @FXML
    private void Registro() throws IOException {
        boolean coincide=false;
        UsuarioDao udao=new UsuarioDao();
        List<Usuario> usuarios=new ArrayList<Usuario>();
        
        Usuario u = new Usuario(
                -1, 
                txtUsername.getText(), 
                txtPassword.getText(), 
                txtPregunta.getText(),
                txtRespuesta.getText()
        );  
            boolean username_ok=u.checkNombre(txtUsername.getText());
            boolean password_ok=u.checkPassword(txtPassword.getText());
            if(txtPassword.getText().equals(txtPassword2.getText())){
                coincide=true;
            }else{
                Alert_Util_1.mostrarError("Las contraseñas no coinciden.");
                coincide=false;
            }
               
            if(username_ok && password_ok && coincide){
                    try{
                        udao.conectar();
                        udao.registroUsuario(u);
                        Alert_Util_1.mostrarInfo("Usuario añadido.");

                    } catch (ClassNotFoundException ex) {
                            Alert_Util_1.mostrarError("1"+ex.getMessage());
                    } catch (SQLException ex) {
                            Alert_Util_1.mostrarError("Prueba con otro nombre, este usuario ya está registrado.");
                    }
            }else{
                Alert_Util_1.mostrarError("Error.");
            }
    }
    
    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("primary");
    }
}
