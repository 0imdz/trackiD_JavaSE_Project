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
 * FXML Controller class
 *
 * @author Ismael
 */
public class PrimaryController {
    
    private ArrayList<Usuario> usuarios;

    @FXML
    private TextField txtUsername;
    
    @FXML
    private Label lblResultado;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private Button btnInicio;
    
    @FXML
    private void switchToRegistro() throws IOException{
        App.setRoot("register");
    }
    
    @FXML
    private void switchToOlvido() throws IOException{
        App.setRoot("olvido");
    }
    
    @FXML
    private void login() throws IOException {
        
        UsuarioDao udao=new UsuarioDao();
        List<Usuario> usuarios=new ArrayList<Usuario>();
        boolean salir=false;
        
        //Usuario u = new Usuario(-1, txtUsername.getText(), txtPassword.getText());
        
        try{
            udao.conectar();
            usuarios=udao.loginUsuario();//necesito meter en arraylist si hago SELECT en clase DAO

              
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError("1"+ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError("2"+ex.getMessage());
        }
        
        for(int i=0; i<usuarios.size(); i++){
            if (txtUsername.getText().equals(usuarios.get(i).getNombre_usuario()) && txtPassword.getText().equals(usuarios.get(i).getPassword())){
                App.setUsuario(usuarios.get(i));//usuario que proviene del arraylist
                App.cargarDatos();
                Alert_Util_1.mostrarInfo(String.valueOf("Has iniciado sesión como "+App.user.getNombre_usuario()));
                salir=true; //salir del for
               
            }
        }
        
        if (!salir){
            Alert_Util_1.mostrarError("Usuario no válido");
        }
        
    } 
}
