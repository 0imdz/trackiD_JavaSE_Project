/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.LanzamientoDao;
import com.mycompany.models.Cancion;
import com.mycompany.models.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
public class NewSongController1 {
    
    private Usuario user;
    private Cancion cancionSel;
    private static LanzamientoDao ldao;
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
    @FXML
    private TextField txtDuracion;
    
    
    @FXML
    private void switchToPerfil() throws IOException{
        App.verPanel();
    }
    
    @FXML
    private void limpiarCancion() {
//        txtUPC.setText("");
        txtTitulo.setText("");
        txtAutoria.setText("");
        cbGenero.setValue("");
        dpCalendario.setValue(java.time.LocalDate.now()); //se pone a fecha de hoy
        txtSello.setText("");
        txtExplicit.setText("");
        txtDuracion.setText("");
        txtExplicit.setText("");
    }
    
    @FXML
    private void insercionCanciones() throws IOException{
        LanzamientoDao ldao = new LanzamientoDao();
        if (this.txtTitulo == null ||
            this.txtAutoria == null ||
            this.cbGenero == null ||
            this.txtSello == null ||
            this.txtExplicit == null ||
            this.txtDuracion == null ||
            this.dpCalendario == null) {
            Alert_Util_1.mostrarInfo("No se permiten campos en blanco."); //duda: no funciona
        }
        Cancion c = new Cancion(
                -1,
                txtTitulo.getText(), 
                txtAutoria.getText(), 
                cbGenero.getValue().toString(), 
                Date.valueOf(dpCalendario.getValue()), 
                txtSello.getText(),
                App.user.getIdusuario(), //idusuario que esté con la sesión iniciada
                txtExplicit.getText().charAt(0), 
                Integer.parseInt(txtDuracion.getText())  
        );
        try {
            ldao.conectar();//conexión a base de datos
            ldao.guardarCancion(c); //para guardar uso de guardarCancion de CancionDao. todo lo guardo en objeto c.
            showCanciones();//refresco: vuelve a ejecutar la función que muestra todas las canciones de un usuario
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
    
    public void showCanciones() throws IOException{//no es @FXML, pq está inicilizado
        LanzamientoDao ldao = new LanzamientoDao();
        List<Cancion> canciones=new ArrayList<Cancion>();
        try {
            ldao.conectar();//meter try-catch
            canciones=ldao.listCancion(App.user.getIdusuario()); //llamada a getIdusuario
            tblCanciones.setItems(FXCollections.observableList(canciones));
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
    
//    private void cargarCancion(Cancion c) {
////        txtUPC.setText(String.valueOf(c.getUpc()));
//        txtTitulo.setText(c.getTitulo());
//        txtAutoria.setText(c.getAutoria());
//        cbGenero.setValue(c.getGenero());
//        dpCalendario.setValue(c.getFecha_lanzamiento().toLocalDate());
//        txtSello.setText(c.getSello());
//        txtExplicit.setText(String.valueOf(c.getC_explicito()));
//        txtDuracion.setText(String.valueOf(c.getDuracion()));
//    }
    
//    @FXML
//    public void seleccionarCancion(Event event) {
//        cancionSel = (Cancion)cbCanciones.getSelectionModel().getSelectedItem();
//        cargarCancion(cancionSel);
//    }

    public void initLists(){
        cbGenero.setItems(generos);
    }
}
