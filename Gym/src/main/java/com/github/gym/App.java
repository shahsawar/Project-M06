package com.github.gym;

import db.GestorPersistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static GestorPersistencia gestorPersistencia;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("configuration"), 900, 480);

        //Añadimos el css
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        //Icono
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/icon.png")));

        //Titulo
        stage.setTitle("GYM");

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}