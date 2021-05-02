/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.models.Cancion;
import com.mycompany.models.Usuario;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class UsuarioDao {
    private Connection conexion;
   
    
    public void conectar() throws ClassNotFoundException, SQLException, IOException{
        String host="localhost";
        String port="3306";
        String dbname="trackid";
        String username="root";
        String password="DBISMAELMARIA99";
        //El connection String quedaría así=jdbc:mariadb://localhost:3306/blockbuster/?serverTimeZone=UTC
        conexion=DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+dbname
                                             +"?serverTimezone=UTC", username, password);
    }
    
    public void desconexion () throws SQLException{
        conexion.close();
    }
    
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
    
    public void registroUsuario(Usuario u) throws SQLException{
        String sql = "INSERT INTO trackid.usuario (NOMBRE_USUARIO, PASSWORD, PREGUNTA_SEGURIDAD, RESPUESTA_SEGURIDAD) VALUES(?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        
        sentencia.setString(1, u.getNombre_usuario());
        sentencia.setString(2, u.getPassword());
        sentencia.setString(3, u.getPregunta());
        sentencia.setString(4, u.getRespuesta());
        sentencia.executeUpdate();
    }
    
    public void updateContrasenya(Usuario u) throws SQLException{
        String sql = "{call update_password (?,?,?,?,?)}";

        CallableStatement sentencia = conexion.prepareCall(sql);
        sentencia.setInt(1, u.getIdusuario());
        sentencia.setString(2, u.getNombre_usuario());
        sentencia.setString(3, u.getPassword());
        sentencia.setString(4, u.getPregunta());
        sentencia.setString(5, u.getRespuesta());
  
        sentencia.execute();
    }
}


