package javafx.gym;

import db.GestorPersistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.Configuracio;
import utilities.Log;

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
        Log.start();//To assign the log file to the log
        Configuracio.getData();//Get the data from the configuration file
    }

    /**
     * Sets the value of the property root.
     *
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load the fxml file
     *
     * @param fxml fxml to load
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Run the application
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}