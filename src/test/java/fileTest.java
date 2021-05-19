/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.models.Cancion;
import com.mycompany.models.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ismael
 */
public class fileTest {
    
    public fileTest() {
    }
    
    /**
     * Test para comprobar que el nombre de usuario ha de ser de longitud mayor 
     * o igual a seis en el proceso de Registro.
     */
    @Test
    public void testRegistro_nombreUsuario() {
        System.out.println("control_nombre");
        String nombre_usuario = "IsMAEL";
        Usuario instance = new Usuario(1, "IsMAEL", "almeria", "hola", "adios");
        boolean expResult = true;
        boolean result = instance.checkNombre(nombre_usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test para comprobar que el password ha de ser de longitud mayor 
     * o igual a ocho e incluir al menos una letra mayúscula en el proceso de Registro.
     */
    @Test
    public void testRegistro_password() {
        System.out.println("control_password");
        String password = "123456789A";
        Usuario instance = new Usuario(1, "Is", "123456789A", "hola", "adios");
        boolean expResult = true;
        boolean result = instance.checkPassword(password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test para comprobar que el array de Strings canciones_1 es de longitud igual a once.
     * En el programa es necesario rellenar el fichero .txt de once parámetros, ya 
     * que este es el número de atributos que tiene Cancion. Aunque pueda parecer 
     * una comprobación irrelevante, se trata de vital importancia, ya que sin este
     * comprobante de longitud no podremos entrar al bucle para revisar cada uno de los
     * parametros que nos pasa el fichero .txt
     */
    @Test
    public void testComprobacion_longitud_array_canciones_1() {
        System.out.println("control_parametros_txt");
        String canciones_1[] = {"Uno","Dos","Tres","Cuatro","Cinco",
        "Seis","Siete","Ocho","Nueve"};
        Cancion instance = new Cancion();
        boolean expResult = false;
        boolean result = instance.comprobacion(canciones_1);
        assertEquals(expResult, result);
    }
    
}
