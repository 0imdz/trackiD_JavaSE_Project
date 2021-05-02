/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.CancionDao;
import com.mycompany.models.Cancion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ismael
 */
public class NewSongController {
    
    
    private ObservableList<Genero> generos = FXCollections.observableArrayList(Arrays.asList(Genero.values()));

    @FXML 
    private TextField txtTitulo;
    @FXML 
    private TextField txtAutoria;
    @FXML 
    private TextField txtFormato;
    @FXML 
    private ComboBox cbGenero;  
    @FXML 
    private TextField txtUPC;
    @FXML 
    private TextField txtSello;
    @FXML 
    private DatePicker dpCalendario;
    @FXML 
    private TextField txtExplicit;
    @FXML 
    private TextField txtNPistas;
    @FXML
    private Button btnConectar;
    @FXML
    private ListView tblCanciones;
    @FXML
    private Button btnAnyadir;
    @FXML
    private Label lblPrueba;
    
//    public void prueba(){
//        lblPrueba.setText(App.user.getNombre_usuario());
//    }
    
    @FXML
    private void insercionCanciones() throws IOException{
        CancionDao cdao = new CancionDao();
        Cancion c = new Cancion(
                Integer.parseInt(txtUPC.getText()), 
                txtTitulo.getText(), 
                txtAutoria.getText(), 
                cbGenero.getValue().toString(), 
                txtFormato.getText(), 
                Date.valueOf(dpCalendario.getValue()), 
                Integer.parseInt(txtNPistas.getText()), 
                txtSello.getText(), 
                txtExplicit.getText().charAt(0),
                App.user.getIdusuario() //idusuario que esté con la sesión iniciada
        );
        try {
            cdao.conectar();//conexión a base de datos
            cdao.guardarCancion(c); //para guardar uso de guardarCancion de CancionDao. todo lo guardo en objeto c.
            showCanciones();//refresco: vuelve a ejecutar la función que muestra todas las canciones de un usuario
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
    
    public void showCanciones() throws IOException{//no es @FXML, pq está inicilizado
        CancionDao cdao = new CancionDao();
        List<Cancion> canciones=new ArrayList<Cancion>();
        try {
            cdao.conectar();//meter try-catch
            canciones=cdao.listCanciones(App.user.getIdusuario()); //llamada a getIdusuario
            tblCanciones.setItems(FXCollections.observableList(canciones));
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }

    public void initLists(){
        cbGenero.setItems(generos);
    }
}
