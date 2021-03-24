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


    boolean userAlreadyExist(String dni){
        boolean exist = false;

        User user = App.gestorPersistencia.getUserByDNI(dni);
        if (user != null){ //Ha devuelto un usuario, por lo tanto existe
            exist = true;
        }

        return exist;
    }

    @FXML
    public void clickSave(ActionEvent actionEvent) {

        System.out.println(nameInput.getText());

        LocalDate localDate = dateInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        System.out.println("DNI: " + dniInput.getText());

        if (userAlreadyExist(dniInput.getText())){
            System.out.println("El usuario ya existe");
        } else {

            App.gestorPersistencia.insertUser(new User(dniInput.getText(), nameInput.getText(), lastnameInput.getText(), date));

            try {
                App.setRoot("MainScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
