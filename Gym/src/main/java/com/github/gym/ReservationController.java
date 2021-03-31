package com.github.gym;

import clases.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @autor shah
 */
public class ReservationController implements Initializable {

    private User userTmp;

    @FXML
    private Label reservationUserDni, reservationUserName, reservationUserLastname;

    private Label l;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUserData(User u){
        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());
        userTmp = u;
    }
}
