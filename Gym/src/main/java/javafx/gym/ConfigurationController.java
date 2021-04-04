package javafx.gym;

import db.*;
import execptions.DatabaseNotAvailableExecption;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import utilities.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    @FXML
    private ImageView imageViewSQL;

    @FXML
    private ImageView imageViewMongo;

    @FXML
    private HBox mysqlContainerBox;

    @FXML
    private HBox mysqlMongoBox;

    /***
     * Back to the main screen
     * @throws IOException
     */
    void backToMain() throws IOException {
        App.setRoot("MainScreen");
    }

    /**
     * @param imageView the image to scale
     * @param bigger    if true, the animation scale will increase the size, if it's false, will decrease
     */
    void scaleSizeAnimation(ImageView imageView, boolean bigger) {
        ScaleTransition st = new ScaleTransition(Duration.millis(100), imageView);

        double initialSize = 1;
        double biggerSize = 1.1;

        if (bigger) {
            st.setFromX(initialSize);
            st.setFromY(initialSize);
            st.setToX(biggerSize);
            st.setToY(biggerSize);
        } else {
            st.setFromX(biggerSize);
            st.setFromY(biggerSize);
            st.setToX(initialSize);
            st.setToY(initialSize);
        }
        st.play();
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

                ConnectionMongo con = new ConnectionMongo();

                try {

                    con.test();

                    App.gestorPersistencia = new GestorPersistenciaMongo();
                    try {
                        backToMain();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (DatabaseNotAvailableExecption e) {
                    System.out.println("Mongodb Connnection not possible");
                    //Convert StackTraceElement to String
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    Log.severe("Could not connect to MongoDB\n" + sw.toString());
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
                try {
                    Connection c = con.start();
                    if (c != null) {
                        App.gestorPersistencia = new GestorPersistenciaJDBC();
                        Log.config("Mysql database set to use");
                        try {
                            backToMain();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    con.close();
                } catch (Exception e) {
                    System.out.println("JDBC Connnection not possible");//No se pone en log porque ya está al ejecutar start()
                }
            }
        });


        //BBDD Images hover
        imageViewMongo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            scaleSizeAnimation(imageViewMongo, true);
        });

        imageViewMongo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            scaleSizeAnimation(imageViewMongo, false);
        });

        imageViewSQL.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            scaleSizeAnimation(imageViewSQL, true);
        });

        imageViewSQL.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            scaleSizeAnimation(imageViewSQL, false);
        });

    }
}
