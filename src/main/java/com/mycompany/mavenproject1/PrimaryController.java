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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class | Controller de ventana de inicio de sesión
 *
 * @author Ismael
 */
public class PrimaryController {
  
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private void switchToRegistro() throws IOException{
        App.setRoot("register");
    }
    
    @FXML
    private void login() throws IOException {
        
        UsuarioDao udao=new UsuarioDao();
        List<Usuario> usuarios=new ArrayList<Usuario>();
        boolean salir=false;
        
        try{
            udao.conectar();
            usuarios=udao.loginUsuario();

              
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError("1"+ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError("2"+ex.getMessage());
        }
        
        for(int i=0; i<usuarios.size(); i++){
            if (txtUsername.getText().equals(usuarios.get(i).getNombre_usuario()) && txtPassword.getText().equals(usuarios.get(i).getPassword())){
                App.setUsuario(usuarios.get(i));
                App.verPanel();
                Alert_Util_1.mostrarInfo(String.valueOf("Has iniciado sesión como "+App.user.getNombre_usuario()));
                salir=true;
            }
        }
        
        if (!salir){
            Alert_Util_1.mostrarError("Usuario no válido");
        }
        
    } 
}
