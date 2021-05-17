/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.LanzamientoDao;
import com.mycompany.models.Cancion;
import com.mycompany.models.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ismael
 */
public class AnyadirCancionController {
    
    private ObservableList<Genero> generos = FXCollections.observableArrayList(Arrays.asList(Genero.values()));

    @FXML 
    private TextField txtTitulo;
    @FXML 
    private TextField txtAutoria;
    @FXML 
    private ComboBox cbGenero;  
    @FXML 
    private TextField txtSello;
    @FXML 
    private DatePicker dpCalendario;
    @FXML 
    private TextField txtExplicit;
    @FXML
    private ListView tblCanciones;
    @FXML
    private TextField txtDuracion;
    @FXML
    private Label lblRuta;
    @FXML
    private Label lblRutaimagen;
    
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
        txtDuracion.setText("");
        txtExplicit.setText("");
        lblRuta.setText("");
    }
    
    @FXML
    private void insercionCanciones() throws IOException{
        LanzamientoDao ldao = new LanzamientoDao();
//        if (this.txtTitulo == null ||
//            this.txtAutoria == null ||
//            this.cbGenero == null ||
//            this.txtSello == null ||
//            this.txtExplicit == null ||
//            this.txtDuracion == null ||
//            this.dpCalendario == null) {
//            Alert_Util_1.mostrarInfo("No se permiten campos en blanco."); //duda: no funciona
//        }
        Cancion c = new Cancion(
                -1,
                txtTitulo.getText(), 
                txtAutoria.getText(), 
                cbGenero.getValue().toString(), 
                Date.valueOf(dpCalendario.getValue()), 
                txtSello.getText(),
                App.user.getIdusuario(), //idusuario que esté con la sesión iniciada
                txtExplicit.getText(), 
                Integer.parseInt(txtDuracion.getText()),
                lblRuta.getText(),
                lblRutaimagen.getText()
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

    public void initLists(){
        cbGenero.setItems(generos);
    }
    
    //AUDIO
    public void leerFichero(){
        
        FileChooser dialogoFichero = new FileChooser();
        dialogoFichero.setTitle("Selecciona un fichero");
        File fAbrir = dialogoFichero.showOpenDialog(null);
//        dialogoFichero.setText(f.getAbsolutePath());
//        File fGuardar = dialogoFichero.showSaveDialog(null);

        if (fAbrir != null) {
            lblRuta.setText(fAbrir.getName());
            try {
                Files.copy(fAbrir.toPath(), (new File(System.getProperty("user.dir")+"/music/" + fAbrir.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AnyadirCancionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    //IMAGEN
    public void leerFicheroImagen(){
        
        FileChooser dialogoFichero1 = new FileChooser();
        dialogoFichero1.setTitle("Selecciona un fichero");
        File fAbrir1 = dialogoFichero1.showOpenDialog(null);
        
        if (fAbrir1 != null) {
            lblRutaimagen.setText(fAbrir1.getName());
            try {
                Files.copy(fAbrir1.toPath(), (new File(System.getProperty("user.dir")+"/images/" + fAbrir1.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING); //copia
            } catch (IOException ex) {
                Logger.getLogger(AnyadirCancionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public void leerFicheroNormal(){
        LanzamientoDao ldao = new LanzamientoDao();
        Cancion c;
        FileChooser dialogoFichero = new FileChooser();
        dialogoFichero.setTitle("Selecciona un fichero");
        File fAbrir = dialogoFichero.showOpenDialog(null);
        //labelFichero.setText(f.getAbsolutePath());
        //File fGuardar = dialogoFichero.showSaveDialog(null);

        if (fAbrir != null) {
            try {
                FileReader lector = new FileReader(fAbrir);
                BufferedReader buffer = new BufferedReader(lector);
                String linea = null;
                String[] canciones_1;
                while((linea = buffer.readLine()) != null){
                    canciones_1=linea.split(",");
                    if(Cancion.comprobacion(canciones_1)){
                        c=new Cancion(canciones_1);
                        try {
                        ldao.conectar();//conexión a base de datos
                        ldao.guardarCancion(c); //para guardar uso de guardarCancion de CancionDao. todo lo guardo en objeto c.
                        
            
                        } catch (ClassNotFoundException ex) {
                            Alert_Util_1.mostrarError(ex.getMessage());
                        } catch (SQLException ex) {
                            Alert_Util_1.mostrarError(ex.getMessage());
                        }
                    }
                }
                //TODO leerlo y cargar los articulos
                lector.close();
                showCanciones();//refresco: vuelve a ejecutar la función que muestra todas las canciones de un usuario
                
            } catch (IOException ex) {
                System.out.println("Excepción capturada");
            }
        }
    }
}
