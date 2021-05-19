package com.mycompany.mavenproject1;

import com.mycompany.models.Cancion;
import com.mycompany.models.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App | clase principal. 
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Clase Usuario accesible desde todas las clases (objeto user)
     */
    public static Usuario user=new Usuario(); 

    /**
     * Clase Cancion accesible desde todas las clases (objeto cancionSel)
     */
    public static Cancion cancionSel=new Cancion();

    /**
     * Primera pantalla. Ventana de inicio de sesión.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        String fxml = "primary";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 900, 550);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    static void verPanel() throws IOException {
        String fxml = "panelUsuario";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        PanelUsuarioController controller = new PanelUsuarioController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
    }
    
    static void cargarDatos() throws IOException {
        String fxml = "actualizarCancion";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        ActualizarCancionController controller = new ActualizarCancionController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
    }
    
    static void anyadido() throws IOException {
        String fxml = "anyadirCancion";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        AnyadirCancionController controller = new AnyadirCancionController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
    }
    
    static void reproducir(Cancion cancionSel) throws IOException {
        String fxml = "reproductor";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        ReproductorController controller = new ReproductorController(cancionSel);
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());

        controller.inicializar();
        
        controller.cargarCancionReproductor(cancionSel);
    }
    
    static void setUsuario(Usuario u) {       
        user = u;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}