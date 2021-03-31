package com.github.gym;

import com.mongodb.client.MongoCollection;
import db.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.bson.Document;
import utilities.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
                Log.info("MongoDB database selected");
                event.consume();

                //Test Connection
                /*ConnectionMongo con = new ConnectionMongo();
                MongoCollection<Document> doc = con.start();

                con.close();*/

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
                Log.info("Mysql database selected");
                event.consume();

                ConnexioJDBC con = new ConnexioJDBC();
                try{
                    Connection c = con.start();

                    if (c != null){
                        App.gestorPersistencia = new GestorPersistenciaJDBC();
                        Log.config("Mysql database set to use");
                        try {
                            backToMain();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    con.close();
                } catch (Exception e){
                    System.out.println("JDBC Connnection not possible");//No se pone en log porque ya está al ejecutar start()
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
