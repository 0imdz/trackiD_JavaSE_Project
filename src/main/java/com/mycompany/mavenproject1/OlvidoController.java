/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.UsuarioDao;
import com.mycompany.models.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Ismael
 */
public class OlvidoController {
    
    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtRespuesta;
    @FXML
    private Button btnInicio;
    @FXML
    private TextField txtPregunta;
    @FXML
    private PasswordField txtPassword2;
    @FXML
    private PasswordField txtPassword1;
    
    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("primary");
    }
    
    @FXML
    private void Restablecer() throws IOException{
        UsuarioDao udao=new UsuarioDao();
        List<Usuario> usuarios=new ArrayList<Usuario>();
        
        Usuario u = new Usuario(
                -1, 
                txtUsername.getText(), 
                txtPassword.getText(), 
                txtPregunta.getText(),
                txtRespuesta.getText()
        );  
    }
}
