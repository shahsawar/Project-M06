package com.github.gym;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {


    @FXML
    private ImageView imageViewSQL;

    @FXML
    private ImageView imageViewMongo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/images/mysql.jpg"));
        Image imageMongo = new Image(getClass().getResourceAsStream("/images/mongodb.jpg"));
        imageViewSQL.setFitHeight(100); //726
        imageViewSQL.setFitWidth(200); //500
        imageViewSQL.setImage(image);
        imageViewMongo.setImage(imageMongo);




        imageViewMongo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mongo selected ");
                event.consume();
            }
        });


        imageViewSQL.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MySQL selected ");
                event.consume();
            }
        });


        imageViewSQL.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("Hover on Mysql detected");
            }
        });

    }


}
