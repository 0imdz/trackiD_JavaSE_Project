package com.mycompany.mavenproject1;

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
//        controller.initLists();
//        controller.prueba();
    }
    
    static void cargarDatos() throws IOException {
        String fxml = "registrar_cancion";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        NewSongController controller = new NewSongController();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
//        controller.prueba();
    }
    
    static void anyadido() throws IOException {
        String fxml = "registrar_cancion_1";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        // Give the controller access to the main app.
        NewSongController1 controller = new NewSongController1();
        fxmlLoader.setController(controller);
        
        scene.setRoot(fxmlLoader.load());
       
        controller.showCanciones();
        controller.initLists();
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