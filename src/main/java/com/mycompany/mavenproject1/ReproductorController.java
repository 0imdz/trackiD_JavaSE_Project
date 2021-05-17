/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.dao.LanzamientoDao;
import com.mycompany.models.Cancion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Ismael
 */
public class ReproductorController {
    private Media media;
    private MediaPlayer mediaPlayer;
    private Cancion cancionSel;
    private Image img;
    
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label tittle;
    @FXML
    private Label lanzamiento;
    @FXML
    private Label sello;
    @FXML
    private Label genre;
    @FXML
    private Label author;
    @FXML
    private Label explicit;
    @FXML
    private Label duration;
    @FXML
    private ImageView ivImagen;
    
    public ReproductorController(Cancion cancionSel){ //sin esto no funcionaría cancionSel
        this.cancionSel=cancionSel;
    }
    
     public void centerImage() {
        img = ivImagen.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = ivImagen.getFitWidth() / img.getWidth();
            double ratioY = ivImagen.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            ivImagen.setX((ivImagen.getFitWidth() - w) / 2);
            ivImagen.setY((ivImagen.getFitHeight() - h) / 2);

        }
    }
    
    public void inicializar(){
        
            cargarCancionReproductor(cancionSel);
            //cambiar cancion mp3 por el dato cancionSel.getAudio (devolviendo un .mp3 de audio)
            File f=new File(System.getProperty("user.dir") + "/music/" +cancionSel.getAudio()); 
            media = new Media(f.toURI().toString());
            mediaPlayer=new MediaPlayer(media);
            //ajustarVolumen
            volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2){
                    mediaPlayer.setVolume(volumeSlider.getValue()*0.01);
                }
            });
            try {
            img = new Image(new FileInputStream(new File(System.getProperty("user.dir") + "/images/"+cancionSel.getImagen())));
            ivImagen.setImage(img);
            centerImage();
        } catch (FileNotFoundException ex) {
            img = new Image(getClass().getResourceAsStream("/images/" + cancionSel.getImagen()));
            ivImagen.setImage(img);
        }  
    }
    
   
    
    @FXML
    public void play(){
        mediaPlayer.play();
    }
    
    @FXML
    public void pause(){
        mediaPlayer.pause();
    }
    
    @FXML
    private void switchToPanel() throws IOException{
        App.verPanel();
    }
    
    public void cargarCancionReproductor(Cancion cancionSel) {
//        txtUPC.setText(String.valueOf(c.getUpc()));
        tittle.setText(cancionSel.getTitulo());
        author.setText(cancionSel.getAutoria());
        genre.setText(cancionSel.getGenero());
        lanzamiento.setText(String.valueOf(cancionSel.getFecha_lanzamiento()));
        sello.setText(cancionSel.getSello());
        explicit.setText(cancionSel.getC_explicito());
        duration.setText(String.valueOf(cancionSel.getDuracion())+" minutos");
////        txtRuta.setText(c.getAudio());
    }
}
