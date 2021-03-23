package com.github.gym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/***
 @author ronald
 */
public class InsertUserController{

    @FXML
    private Button cancelBtn;


    public void initialize() {

        /*
        //Bind entre controles
        textNom.textProperty()
                .bindBidirectional(textNomFiscal.textProperty());

        //Bind de controles y atributos de la clase
        nombre.bind(textNom.textProperty());
        nomFiscalText.bind(textNomFiscal.textProperty());
        direccionText.bind(direccion.textProperty());
        ciudadText.bind(ciudad.textProperty());
        provinciaText.bind(provincia.textProperty());*/

        //App.gestorPersistencia.getAllUsers();

    }


    @FXML
    public void clickCancel(ActionEvent actionEvent) {
        try {
            App.setRoot("MainScreen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
