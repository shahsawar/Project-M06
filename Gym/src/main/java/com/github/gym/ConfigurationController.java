package com.github.gym;

import db.GestorPersistencia;
import db.GestorPersistenciaJDBC;
import db.GestorPersistenciaMongo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    @FXML
    private ImageView imageViewSQL;

    @FXML
    private ImageView imageViewMongo;

    /***
     * Back
     * @throws IOException
     */
    void backToMain() throws IOException {
        App.setRoot("MainScreen");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image(getClass().getResourceAsStream("/images/mysql.png"));
        Image imageMongo = new Image(getClass().getResourceAsStream("/images/mongo.png"));
        imageViewSQL.setFitHeight(100); //726
        imageViewSQL.setFitWidth(200); //500
        imageViewSQL.setImage(image);
        imageViewMongo.setImage(imageMongo);

        //MongoDB selected
        imageViewMongo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mongo selected ");
                event.consume();
                App.gestorPersistencia = new GestorPersistenciaMongo();
                try {
                    backToMain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Mysql selected
        imageViewSQL.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MySQL selected ");
                event.consume();
                App.gestorPersistencia = new GestorPersistenciaJDBC();
                try {
                    backToMain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //Hover
        imageViewSQL.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Hover on Mysql detected");
            }
        });

    }


}
