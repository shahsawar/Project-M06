package com.github.gym;

import clases.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.DataValidator;

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

    @FXML

    private ImageView newUserIlustration;


    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/images/newUser.png"));
        newUserIlustration.setImage(image);
    }

    static void showPopUp(String message){
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
        alert.setTitle("Alert");
        alert.showAndWait();
    }


    @FXML
    public void clickSave(ActionEvent actionEvent) {

        LocalDate localDate = dateInput.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        boolean canInsertUser = true;

        //Check if user already exist
        User userTmp = App.gestorPersistencia.getUserByDNI(dniInput.getText());

        if (userTmp != null){

            showPopUp("User already exist");
            canInsertUser = false;

        } else if (!DataValidator.isDNICorrect(dniInput.getText())){
            showPopUp("DNI is incorrect");
            canInsertUser = false;

        } else if (!DataValidator.isName_LastNameCorrect(nameInput.getText())){
            showPopUp("Name is incorrect");
            canInsertUser = false;
        } else if (!DataValidator.isName_LastNameCorrect(lastnameInput.getText())){
            showPopUp("Lastname is incorrect");
            canInsertUser = false;
        }

        if (canInsertUser){
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
