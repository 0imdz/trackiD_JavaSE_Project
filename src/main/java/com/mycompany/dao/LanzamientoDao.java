/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.mavenproject1.App;
import com.mycompany.models.Cancion;
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
 * Clase para realizar conexión a base de datos
 * @author Ismael
 */
public class LanzamientoDao {
    private Connection conexion;
  
    /**
     * Establecimiento de parámetros necesarios para que se efectúe la conexión con la base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void conectar() throws ClassNotFoundException, SQLException, IOException{
        
        Properties configuration = new Properties();
        configuration.load(new FileInputStream(new File(App.class.getResource("connectionDB.properties").getPath())));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String dbname = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");
        
        conexion=DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+dbname
                                             +"?serverTimezone=UTC", username, password);
    }
    
    /**
     * Cierre de conexión con la base de datos
     * @throws SQLException
     */
    public void desconexion () throws SQLException{
        conexion.close();
    }
    
    /**
     * Método que guarda las canciones insertadas en la aplicación en la base de datos
     * @param c
     * @throws SQLException
     */
    public void guardarCancion(Cancion c) throws SQLException{
        String sql = "INSERT INTO trackid.cancion (TITULO, AUTORIA, GENERO, FECHA_LANZAMIENTO, SELLO, C_EXPLICITO, DURACION, USUARIO_ID, AUDIO, IMAGEN) VALUES(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        
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
    
    /**
     * Método que muestra todos los elementos de la tabla canciones de la base de datos en pantalla
     * @param usuario_id
     * @return
     * @throws SQLException
     */
    public List<Cancion> listCancion(int usuario_id) throws SQLException {
        
    List<Cancion> canciones = new ArrayList<>();
    String sql = "SELECT * FROM cancion WHERE USUARIO_ID=?"; 

    PreparedStatement sentencia = conexion.prepareStatement(sql);
    
    sentencia.setInt(1, usuario_id);
    
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
        }
        return canciones;
    }
    
    /**
     * Método que llama a un proceso almacenado en la base de datos:
     *  modifica los parámetros del objeto
     * @param c
     * @throws SQLException
     */
    public void modificarCancion(Cancion c) throws SQLException {
        String sql = "{call spLanzamientoModified (?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement sentencia = conexion.prepareCall(sql);
        sentencia.setInt(1, c.getUpc());
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
    
    /**
     * Método que borra una canción determinada de la base de datos
     * @param c
     * @throws SQLException
     */
    public void deleteCancion(Cancion c) throws SQLException {
        String sql = "DELETE FROM CANCION WHERE UPC = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, c.getUpc());
        sentencia.executeUpdate();
    }
}


