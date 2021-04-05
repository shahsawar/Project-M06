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

import java.io.PrintWriter;
import java.io.StringWriter;
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

    /**
     * User data
     */
    @FXML
    private Label reservationUserDni, reservationUserName, reservationUserLastname;

    /**
     * Reservation tableview
     */
    @FXML
    private TableView<Reservation> reservationTableView;

    /**
     * Reservation table date column
     */
    @FXML
    private TableColumn<Reservation, String> colReservationDate;

    /**
     * Reservation table room name column
     */
    @FXML
    private TableColumn<Reservation, String> colReservationRoomname;

    /**
     * Reservation table workout plane column
     */
    @FXML
    private TableColumn<Reservation, Boolean> colReservationWorkoutplane;


    /**
     * Reservation form date column
     */
    @FXML
    private DatePicker reservationInputDate;

    /**
     * Reservation form room name column
     */
    @FXML
    private TextField reservationInputRoomname;

    /**
     * Reservation form radio button
     */
    @FXML
    private RadioButton radioBtn1, radioBtn2;


    //Close scene
    @FXML
    private Button back;

    ObservableList<Reservation> observableList = FXCollections.observableArrayList();
    List<Reservation> reservationList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Radion button group
        ToggleGroup group = new ToggleGroup();
        radioBtn1.setToggleGroup(group);
        radioBtn2.setToggleGroup(group);
    }

    /**
     * Assign user data to screen reservation
     * @param u {@link clases.User}
     */
    public void setUserData(User u) {

        //Set user info to labels
        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());

        //Assign the received user to userTmp
        userTmp = u;

        //Get all user reservations and assign them to tableview
        reservationList = App.gestorPersistencia.getReservationsByUser(userTmp);
        observableList.addAll(reservationList);
        //colReservationDate.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("date"));
        colReservationDate.setCellValueFactory(new FormattedDateValueFactory<Reservation>("date", "dd/MM/yyyy"));
        colReservationRoomname.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colReservationWorkoutplane.setCellValueFactory(new PropertyValueFactory<>("workoutPlane"));

        //Set observableList to tableView
        reservationTableView.setItems(observableList);

        //Make tableView editable
        reservationTableView.setEditable(true);

        //Allow user to edit only the following columns
        colReservationRoomname.setCellFactory(TextFieldTableCell.forTableColumn());
        colReservationDate.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /**
     * Update reservation room name
     * @param reservationStringCellEditEvent
     */
    public void updateRoomName(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {
        Reservation reservation = reservationTableView.getSelectionModel().getSelectedItem();
        reservation.setRoomName(reservationStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateReservation(reservation, reservation.getUserCode());
    }

    /**
     * Update reservation date
     * @param reservationStringCellEditEvent
     */
    public void updateDate(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {
        Reservation reservation = reservationTableView.getSelectionModel().getSelectedItem();

        //String to date
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(reservationStringCellEditEvent.getNewValue());

            reservation.setDate(date);
            App.gestorPersistencia.updateReservation(reservation, reservation.getUserCode());
        } catch (ParseException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.severe("Date " + reservationStringCellEditEvent.getNewValue() + " is not valid \n" + sw.toString());

            Alert alert = new Alert(Alert.AlertType.NONE, "Date " + reservationStringCellEditEvent.getNewValue() + " is not valid", ButtonType.OK);
            alert.setTitle("Alert");
            alert.showAndWait();
        }
    }

    /**
     * Close current screen
     */
    @FXML
    private void closeScene(){
        // get a handle to the stage
        Stage stage = (Stage) back.getScene().getWindow();
        //Close the window
        stage.close();
    }

    /**
     * Add reservation
     * @param actionEvent
     */
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

            //Remove data from the form when inserting a reservation
            reservationInputRoomname.clear();
            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            reservationInputDate.getEditor().clear();
        }
    }

    /**
     * Remove selected reservation
     * @param actionEvent
     */
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