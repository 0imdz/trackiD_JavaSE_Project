/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.LanzamientoDao;
import com.mycompany.models.Cancion;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Ismael
 */
public class PanelUsuarioController {
    
    private Cancion cancionSel;
    
    @FXML
    private ListView tblCancionesPerfil;
    
    @FXML
    private void switchToAnyadir() throws IOException{
        App.cargarDatos();
    }
    
    @FXML
    private void switchToAnyadirSong() throws IOException{
        App.anyadido();
    }
    
    @FXML
    private Label lblUsuario;
    
    @FXML
    private void seleccionarCancionPerfil(){
        cancionSel = (Cancion)tblCancionesPerfil.getSelectionModel().getSelectedItem();
        try {
            App.reproducir(cancionSel);
        } catch (IOException ex) {
            Alert_Util_1.mostrarError("Error al cargar la ventana.");
        }
    }
        
    public void showCanciones() throws IOException{//no es @FXML, pq está inicilizado
        LanzamientoDao ldao = new LanzamientoDao();
        List<Cancion> canciones=new ArrayList<Cancion>();
        try {
            ldao.conectar();//meter try-catch
            canciones=ldao.listCancion(App.user.getIdusuario()); //llamada a getIdusuario
            tblCancionesPerfil.setItems(FXCollections.observableList(canciones));
            lblUsuario.setText("@ "+App.user.getNombre_usuario());
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
}
