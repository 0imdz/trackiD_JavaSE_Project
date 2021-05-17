/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.mavenproject1.App;
import com.mycompany.models.Cancion;
import com.mycompany.models.Lanzamiento;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Ismael
 */
public class LanzamientoDao {
    private Connection conexion;
  
    public void conectar() throws ClassNotFoundException, SQLException, IOException{
//        String host="localhost";
//        String port="3306";
//        String dbname="trackid";
//        String username="root";
//        String password="DBISMAELMARIA99";
        
        Properties configuration = new Properties();//objeti clase Properties
        configuration.load(new FileInputStream(new File(App.class.getResource("connectionDB.properties").getPath())));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String dbname = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        
        //El connection String quedaría así=jdbc:mariadb://localhost:3306/blockbuster/?serverTimeZone=UTC
        conexion=DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+dbname
                                             +"?serverTimezone=UTC", username, password);
    }
    
    public void desconexion () throws SQLException{
        conexion.close();
    }
    
    public void guardarCancion(Cancion c) throws SQLException{
        String sql = "INSERT INTO trackid.cancion (TITULO, AUTORIA, GENERO, FECHA_LANZAMIENTO, SELLO, C_EXPLICITO, DURACION, USUARIO_ID, AUDIO, IMAGEN) VALUES(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
//        sentencia.setInt( 1, c.getUpc());
        sentencia.setString(1, c.getTitulo());
        sentencia.setString(2, c.getAutoria());
        sentencia.setString(3, c.getGenero());
        sentencia.setDate(4, c.getFecha_lanzamiento());
        sentencia.setString(5, c.getSello());
        sentencia.setString(6, c.getC_explicito());
        sentencia.setInt(7, c.getDuracion());
        sentencia.setInt(8, c.getUsuario_id());
        sentencia.setString(9, c.getAudio());
        sentencia.setString(10, c.getImagen());
        sentencia.executeUpdate();
    }   
    
    public List<Cancion> listCancion(int usuario_id) throws SQLException { //no declaro int idusuario
                                                               //pq lo paso en App.user.getIdusuario()
        
    List<Cancion> canciones = new ArrayList<>();
    String sql = "SELECT * FROM cancion WHERE USUARIO_ID=?"; //interrogante pq se le pasa abajo el id de usuario
                                                                                         //el getIdusuario trae el id de usuario que busca la consulta 

    PreparedStatement sentencia = conexion.prepareStatement(sql);
    
    sentencia.setInt(1, usuario_id); //le paso atributo de tipo int pq usuario_id es de tipo int
                                     //el uno es por la posicion del idusuario en clase usuario, es lo importante
                                     //set tipo de variable que quiero
                                     //este sería el valor del interrogante
    
    ResultSet resultado = sentencia.executeQuery();

        while(resultado.next()){
            Cancion c = new Cancion();
            c.setUpc(resultado.getInt(1)); 
            c.setTitulo(resultado.getString(2));
            c.setAutoria(resultado.getString(3));
            c.setGenero(resultado.getString(4));
            c.setFecha_lanzamiento(resultado.getDate(5));
            c.setSello(resultado.getString(6));
            c.setC_explicito(resultado.getString(7));
            c.setDuracion(resultado.getInt(8));
            c.setUsuario_id(resultado.getInt(9));
            c.setAudio(resultado.getString(10));
            c.setImagen(resultado.getString(11));
            canciones.add(c);
            //(resultado está ejecutando la query de la db)se añade a arraylist canciones el valor de UPC
            //¿de dónde sale el valor dado a variable UPC?: a que resultado(variable que prepara y ejecuta cada query)
            //pide lo contenido en la posición dada (posición en la base de datos(>1)
        }
        return canciones;
    }
    
    public void modificarCancion(Cancion c) throws SQLException {
        String sql = "{call spLanzamientoModified (?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement sentencia = conexion.prepareCall(sql);
        sentencia.setInt(1, c.getUpc());//no permito que se produzca la modificacion del upc
        sentencia.setString(2, c.getTitulo());
        sentencia.setString(3, c.getAutoria());
        sentencia.setString(4, c.getGenero());
        sentencia.setDate(5, c.getFecha_lanzamiento());
        sentencia.setString(6, c.getSello());
        sentencia.setString(7, c.getC_explicito());
        sentencia.setInt(8, c.getDuracion());
        sentencia.setString(9, c.getAudio());
        sentencia.setString(10, c.getImagen());
        sentencia.executeUpdate();
  
        sentencia.execute();
    }
    
    public void deleteCancion(Cancion c) throws SQLException {
        String sql = "DELETE FROM CANCION WHERE UPC = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, c.getUpc());
        sentencia.executeUpdate();
    }
}


