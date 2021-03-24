package com.github.gym;

import clases.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/***
 @author ronald
 */
public class InsertUserController{

    private final StringProperty dni = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty lastName = new SimpleStringProperty("");
    private final StringProperty birthdate = new SimpleStringProperty("");

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField dniInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField lastnameInput;

    @FXML
    private DatePicker dateInput;


    public void initialize() {

        //Bind
        dni.bind(dniInput.textProperty());
        name.bind(nameInput.textProperty());
        lastName.bind(lastnameInput.textProperty());
        //dateInput.bind(dateInput.textProperty());
    }


    @FXML
    public void clickSave(ActionEvent actionEvent) {

        System.out.println(name.get());

        LocalDate localDate = dateInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);


        /*
        App.gestorPersistencia.insertUser(new User(dni.get(), name.get(), lastName.get(), date));

        try {
            App.setRoot("MainScreen");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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
