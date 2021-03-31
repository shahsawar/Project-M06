package com.github.gym;

import clases.Reservation;
import clases.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @autor shah
 */
public class ReservationController implements Initializable {

    private User userTmp;

    @FXML
    private Label reservationUserDni, reservationUserName, reservationUserLastname;

    @FXML
    private TableView<Reservation> reservationTableView;

    @FXML
    private TableColumn<Reservation, String> colReservationDate;

    @FXML
    private TableColumn<Reservation, String> colReservationRoomname;

    @FXML
    private TableColumn<Reservation, Boolean> colReservationWorkoutplane;

    ObservableList<Reservation> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Reservation> reservationList = userTmp.getReservations();
        observableList.addAll(reservationList);

        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("dateStr"));
    }

    public void setUserData(User u) {
        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());
        userTmp = u;
    }
}
