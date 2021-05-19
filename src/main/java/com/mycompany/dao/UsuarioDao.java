/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.mavenproject1.App;
import com.mycompany.models.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Clase que conecta con la tabla Usuario de la base de datos
 * @author Ismael
 */
public class UsuarioDao {
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
     * Método que realiza el inicio de sesión mediante consulta a la base de datos Usuario
     * @return
     * @throws SQLException
     */
    public List<Usuario> loginUsuario() throws SQLException {
        
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        
        ResultSet resultado = sentencia.executeQuery();
        
        while(resultado.next()){
            Usuario u=new Usuario();
            
            u.setIdusuario(resultado.getInt(1));
            u.setNombre_usuario(resultado.getString(2));
            u.setPassword(resultado.getString(3));
            
            
            usuarios.add(u);
        }

    return usuarios;   
    }
    
    /**
     * Método que realiza el registro mediante consulta a la base de datos Usuario
     * @param u
     * @throws SQLException
     */
    public void registroUsuario(Usuario u) throws SQLException{
        String sql = "INSERT INTO trackid.usuario (NOMBRE_USUARIO, PASSWORD, PREGUNTA_SEGURIDAD, RESPUESTA_SEGURIDAD) VALUES(?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        
        sentencia.setString(1, u.getNombre_usuario());
        sentencia.setString(2, u.getPassword());
        sentencia.setString(3, u.getPregunta());
        sentencia.setString(4, u.getRespuesta());
        sentencia.executeUpdate();
    }
}


