package javafx.gym;

import clases.Reservation;
import clases.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import llibreries.FormattedDateValueFactory;
import utilities.Log;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @autor shah
 */
public class ReservationController implements Initializable {

    User userTmp;

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


    //Add reservation
    @FXML
    private DatePicker reservationInputDate;

    @FXML
    private TextField reservationInputRoomname;

    @FXML
    private RadioButton radioBtn1, radioBtn2;

    //Close scene
    @FXML
    private Button back;

    ObservableList<Reservation> observableList = FXCollections.observableArrayList();

    List<Reservation> reservationList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup group = new ToggleGroup();
        radioBtn1.setToggleGroup(group);
        radioBtn2.setToggleGroup(group);

    }

    public void setUserData(User u) {

        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());
        userTmp = u;

        reservationList = App.gestorPersistencia.getReservationsByUser(userTmp);
        observableList.addAll(reservationList);
        //colReservationDate.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date"));
        colReservationDate.setCellValueFactory(new FormattedDateValueFactory<Reservation>("date", "dd/MM/yyyy"));
        colReservationRoomname.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colReservationWorkoutplane.setCellValueFactory(new PropertyValueFactory<>("workoutPlane"));

        reservationTableView.setItems(observableList);
        reservationTableView.setEditable(true);
        colReservationRoomname.setCellFactory(TextFieldTableCell.forTableColumn());
        colReservationDate.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void updateRoomName(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {
        Reservation reservation = reservationTableView.getSelectionModel().getSelectedItem();
        reservation.setRoomName(reservationStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateReservation(reservation, reservation.getUserCode());
    }

    public void updateDate(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {
        Reservation reservation = reservationTableView.getSelectionModel().getSelectedItem();

        //String to date
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(reservationStringCellEditEvent.getNewValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setDate(date);
        App.gestorPersistencia.updateReservation(reservation, reservation.getUserCode());
    }

    @FXML
    private void closeScene(){
        // get a handle to the stage
        Stage stage = (Stage) back.getScene().getWindow();
        //Close the window
        stage.close();
    }

    @FXML
    public void add(ActionEvent actionEvent) {

        //LocalDate to date
        if (reservationInputDate.getValue() == null ){

            Alert alert = new Alert(Alert.AlertType.NONE, "Reservation date is not set", ButtonType.OK);
            alert.setTitle("Alert");
            alert.showAndWait();

        } else {

            LocalDate localDate = reservationInputDate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            boolean workOutPlane = false;
            if (radioBtn1.isSelected()) {
                workOutPlane = true;
            }

            //Create and save reservation
            Reservation reservationTmp = new Reservation(date, userTmp.getUserCode(), reservationInputRoomname.getText(), workOutPlane);
            App.gestorPersistencia.insertReservation(reservationTmp);

            //Add reservation to tableView
            observableList.add(reservationTmp);

            //Remove form data
            reservationInputRoomname.clear();
            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            reservationInputDate.getEditor().clear();

        }

    }

    public void remove(ActionEvent actionEvent) {

        //Reservation selected to be remove
        //ObservableList<Reservation> selectedReservation = reservationTableView.getSelectionModel().getSelectedItems();
        int selectedItems = reservationTableView.getSelectionModel().getSelectedItems().size();

        if (selectedItems == 0){
            Log.warning("No reservation selected, can't delete");

            //Monstramos la alerta
            Alert alert = new Alert(Alert.AlertType.NONE, "No reservation selected, can't delete", ButtonType.OK);
            alert.setTitle("No reservation selected");
            alert.showAndWait();

        } else {

            //User selected to be remove
            ObservableList<Reservation> selectedReservation = reservationTableView.getSelectionModel().getSelectedItems();

            Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to delete the reservation ?", ButtonType.NO, ButtonType.YES);
            alert.setTitle("Are you sure?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                //Remove reservation from database
                Reservation reservationTmp = selectedReservation.get(0);
                App.gestorPersistencia.deleteReservation(reservationTmp);

                //Remove reservation from tableview
                selectedReservation.forEach(observableList::remove);

                //Remove from the array
                reservationList.remove(reservationTmp);
            }
        }

    }
}