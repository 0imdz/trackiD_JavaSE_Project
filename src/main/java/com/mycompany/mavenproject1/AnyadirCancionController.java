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
 * Clase controller del proceso de añadido de los parámetros de la canción
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
        dpCalendario.setValue(java.time.LocalDate.now()); 
        txtSello.setText("");
        txtDuracion.setText("");
        txtExplicit.setText("");
        lblRuta.setText("");
    }
    
    @FXML
    private void insercionCanciones() throws IOException{
        LanzamientoDao ldao = new LanzamientoDao();
        Cancion c = new Cancion(
                -1,
                txtTitulo.getText(), 
                txtAutoria.getText(), 
                cbGenero.getValue().toString(), 
                Date.valueOf(dpCalendario.getValue()), 
                txtSello.getText(),
                App.user.getIdusuario(),
                txtExplicit.getText(), 
                Integer.parseInt(txtDuracion.getText()),
                lblRuta.getText(),
                lblRutaimagen.getText()
        );
        try {
            ldao.conectar();
            ldao.guardarCancion(c);
            showCanciones();
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
    
    /**
     * Método que muestra las canciones contenidas en la base de datos Canciones. 
     * Para poder manejarlas usaremos un arraylist en la aplicación Java
     * @throws IOException
     */
    public void showCanciones() throws IOException{
        LanzamientoDao ldao = new LanzamientoDao();
        List<Cancion> canciones=new ArrayList<Cancion>();
        try {
            ldao.conectar();
            canciones=ldao.listCancion(App.user.getIdusuario()); 
            tblCanciones.setItems(FXCollections.observableList(canciones));
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }

    /**
     * Se carga el comboBox cbGenero con los diferentes géneros que trackiD permite.
     * Estos aparecen almacenados en una clase enum (Género), la cual aparecerá como
     * clase de un ObservableList de nombre generos.
     */
    public void initLists(){
        cbGenero.setItems(generos);
    }

    /**
     * LECTURA FICHERO DE AUDIO (.MP3, .WAV)
     */
    public void leerFichero(){
        
        FileChooser dialogoFichero = new FileChooser();
        dialogoFichero.setTitle("Selecciona un fichero");
        File fAbrir = dialogoFichero.showOpenDialog(null);

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

    /**
     * LECTURA DE FICHERO DE IMAGEN (.JPG, .PNG, .GIF)
     */
    public void leerFicheroImagen(){
        
        FileChooser dialogoFichero1 = new FileChooser();
        dialogoFichero1.setTitle("Selecciona un fichero");
        File fAbrir1 = dialogoFichero1.showOpenDialog(null);
        
        if (fAbrir1 != null) {
            lblRutaimagen.setText(fAbrir1.getName());
            try {
                Files.copy(fAbrir1.toPath(), (new File(System.getProperty("user.dir")+"/images/" + fAbrir1.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING); 
            } catch (IOException ex) {
                Logger.getLogger(AnyadirCancionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    /**
     * LECTURA DE FICHERO DE TEXTO (.TXT)
     */
    public void leerFicheroNormal(){
        LanzamientoDao ldao = new LanzamientoDao();
        Cancion c;
        FileChooser dialogoFichero = new FileChooser();
        dialogoFichero.setTitle("Selecciona un fichero");
        File fAbrir = dialogoFichero.showOpenDialog(null);

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
                        ldao.conectar();
                        ldao.guardarCancion(c); 
                        
                        } catch (ClassNotFoundException ex) {
                            Alert_Util_1.mostrarError(ex.getMessage());
                        } catch (SQLException ex) {
                            Alert_Util_1.mostrarError(ex.getMessage());
                        }
                    }
                }
                lector.close();
                showCanciones();
                
            } catch (IOException ex) {
                System.out.println("Excepción capturada");
            }
        }
    }
}
