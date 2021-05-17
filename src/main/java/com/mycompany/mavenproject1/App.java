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
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Usuario user=new Usuario(); //este tiene que estar public para ser accesible desde las otras clases
    public static Cancion cancionSel=new Cancion();

    @Override
    public void start(Stage stage) throws IOException {
        String fxml = "primary";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene = new Scene(fxmlLoader.load(), 900, 550);
        stage.setScene(scene);
        stage.show();
        
        //Controlador accede a App
//        PrimaryController controller = fxmlLoader.getController();
////        scene.setRoot(fxmlLoader.load());
//        controller.loadUsers();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    static void verPanel() throws IOException {
        String fxml = "panelUsuario";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        PanelUsuarioController controller = new PanelUsuarioController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
//        controller.guardarCancionFromPerfil(cancionSel);
//        controller.initLists();
//        controller.prueba();
    }
    
    static void cargarDatos() throws IOException {
        String fxml = "actualizarCancion";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        ActualizarCancionController controller = new ActualizarCancionController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
//        controller.prueba();
    }
    
    static void anyadido() throws IOException {
        String fxml = "anyadirCancion";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        AnyadirCancionController controller = new AnyadirCancionController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
//        controller.prueba();
    }
    
    static void reproducir(Cancion cancionSel) throws IOException {
        String fxml = "reproductor";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        ReproductorController controller = new ReproductorController(cancionSel);
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
//        controller.showCanciones();

        controller.inicializar();
        
//        controller.play();
//        controller.pause();
        
        controller.cargarCancionReproductor(cancionSel);
//        controller.prueba();
    }
    
//    static void registrarUsuario() throws IOException {
//        String fxml = "register";
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//
//        // Give the controller access to the main app.
//        RegisterController controller = new RegisterController();
//        fxmlLoader.setController(controller);
//        controller.initPreguntas();
////        scene.setRoot(fxmlLoader.load());
//       
//        
//        
//    }
    
    static void setUsuario(Usuario u) {       
        user = u;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}