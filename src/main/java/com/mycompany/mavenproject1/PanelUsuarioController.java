/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.LanzamientoDao;
import com.mycompany.models.Cancion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author Ismael
 */
public class PanelUsuarioController {
    
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
    
    
    public void showCanciones() throws IOException{//no es @FXML, pq está inicilizado
        LanzamientoDao ldao = new LanzamientoDao();
        List<Cancion> canciones=new ArrayList<Cancion>();
        try {
            ldao.conectar();//meter try-catch
            canciones=ldao.listCancion(App.user.getIdusuario()); //llamada a getIdusuario
            tblCancionesPerfil.setItems(FXCollections.observableList(canciones));
            
        } catch (ClassNotFoundException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        } catch (SQLException ex) {
            Alert_Util_1.mostrarError(ex.getMessage());
        }
    }
}
