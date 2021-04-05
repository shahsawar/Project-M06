package javafx.gym;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import clases.*;
import javafx.stage.Stage;
import llibreries.FormattedDateValueFactory;
import javafx.util.Duration;
import utilities.Log;

/**
 * @autor shah, Ronald
 */
public class MainScreen implements Initializable {

    /**
     * Configuration image
     */
    @FXML
    private ImageView imagenMainScreen;

    /**
     * Redirect to inserUser screen
     */
    @FXML
    private Button mainScreenAddUserBtn;

    /**
     * Field name
     */
    @FXML
    private TextField textFieldName;

    /**
     * TableView
     */
    @FXML
    private TableView<User> mainScreenTable;

    /**
     * Dni column
     */
    @FXML
    private TableColumn<User, String> colUserDni;

    /**
     * Name column
     */
    @FXML
    private TableColumn<User, String> colUserName;

    /**
     * Last name column
     */
    @FXML
    private TableColumn<User, String> colUserLastname;

    /**
     * Birthdate column
     */
    @FXML
    private TableColumn<User, String> colUserbirthdate;

    ObservableList<User> observableList = FXCollections.observableArrayList();
    List<User> userList = App.gestorPersistencia.getAllUsers();


    /**
     * Load all database users in tableview
     *
     * @param url            url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Icon
        Image image = new Image(getClass().getResourceAsStream("/images/settings.png"));
        imagenMainScreen.setImage(image);

        //Add all users to observableList
        observableList.addAll(userList);

        //Extract information from user object and assign it to columns
        colUserDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colUserbirthdate.setCellValueFactory(new FormattedDateValueFactory<User>("birthDate", "dd/MM/yyyy"));

        //Set observableList to tableView
        mainScreenTable.setItems(observableList);

        //Make tableView editable
        mainScreenTable.setEditable(true);

        //Allow user to edit only the following columns
        colUserName.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserbirthdate.setCellFactory(TextFieldTableCell.forTableColumn());

        //Icon roatation animation
        RotateTransition rt = new RotateTransition(Duration.millis(3000), imagenMainScreen);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);

        //Hover
        imagenMainScreen.setOnMouseEntered(e -> rt.play());
        imagenMainScreen.setOnMouseExited(e -> rt.pause());

        //Redirect to configuration screen
        imagenMainScreen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("configuration");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.consume();
            }
        });

        //Redirect to inserUser screen
        mainScreenAddUserBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    App.setRoot("insertUser");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Update user name
     *
     * @param userStringCellEditEvent
     */
    public void updateUserName(TableColumn.CellEditEvent<User, String> userStringCellEditEvent) {
        User user = mainScreenTable.getSelectionModel().getSelectedItem();
        user.setName(userStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateUser(user, user.getUserCode());
    }

    /**
     * Update user last name
     *
     * @param userStringCellEditEvent
     */
    public void updateUserLastname(TableColumn.CellEditEvent<User, String> userStringCellEditEvent) {
        User user = mainScreenTable.getSelectionModel().getSelectedItem();
        user.setLastname(userStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateUser(user, user.getUserCode());
    }

    /**
     * Update user birthdate
     *
     * @param reservationStringCellEditEvent
     */
    public void updateBirthdate(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {

        //Get selected user
        User user = mainScreenTable.getSelectionModel().getSelectedItem();
        Date date = null;
        try {
            //String to date
            date = new SimpleDateFormat("dd/MM/yyyy").parse(reservationStringCellEditEvent.getNewValue());
            user.setBirthDate(date);

            App.gestorPersistencia.updateUser(user, user.getUserCode());

        } catch (ParseException e) {
            //Convert StackTraceElement to String
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.severe("Date " + reservationStringCellEditEvent.getNewValue() + " is not valid \n" + sw.toString());

            Alert alert = new Alert(Alert.AlertType.NONE, "Date " + reservationStringCellEditEvent.getNewValue() + " is not valid", ButtonType.OK);
            alert.setTitle("Alert");
            alert.showAndWait();
        }
    }

    /**
     * Remove user
     *
     * @param actionEvent
     */
    public void removeUser(ActionEvent actionEvent) {

        int selectedItems = mainScreenTable.getSelectionModel().getSelectedItems().size();

        if (selectedItems == 0) {
            Log.warning("Not user selected, can't delete");

            //Monstramos la alerta
            Alert alert = new Alert(Alert.AlertType.NONE, "No user selected, can't delete", ButtonType.OK);
            alert.setTitle("No user selected");
            alert.showAndWait();

        } else {

            //User selected to be remove
            ObservableList<User> selectedUser = mainScreenTable.getSelectionModel().getSelectedItems();

            User userTmp = App.gestorPersistencia.getUserById(selectedUser.get(0).getUserCode());

            Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to delete user " + userTmp.getName() + " with dni: " + userTmp.getDni(), ButtonType.NO, ButtonType.YES);
            alert.setTitle("Are you sure?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                List<Reservation> userReservations = userTmp.getReservations();
                if (userReservations.size() > 0) {//Eliminamos sus reservas, necesario para JDBC
                    for (Reservation reservation : userReservations) {
                        App.gestorPersistencia.deleteReservation(reservation);
                    }
                }

                //Remove user from BBDD
                App.gestorPersistencia.deleteUser(userTmp);

                //Remove user from tableview
                selectedUser.forEach(observableList::remove);

                //Remove from the array
                userList.remove(userTmp);
            }
        }
    }

    /**
     * Redirect to AddReservation screen
     *
     * @param event
     */
    @FXML
    private void addReservation(ActionEvent event) {

        int selectedItems = mainScreenTable.getSelectionModel().getSelectedItems().size();

        if (selectedItems == 0) {
            Log.warning("Not user selected, can't add reservation");

            //Monstramos la alerta
            Alert alert = new Alert(Alert.AlertType.NONE, "No user selected, can't add reservation", ButtonType.OK);
            alert.setTitle("No user selected");
            alert.showAndWait();

        } else {
            //User selected
            User userSelected = mainScreenTable.getSelectionModel().getSelectedItems().get(0);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reservation.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Pass the selected user to reservation controller
            ReservationController r = loader.getController();
            r.setUserData(userSelected);

            //Show new screen
            Parent p = loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Search user by name
     *
     * @param event
     */
    @FXML
    private void searchUserByName(ActionEvent event) {
        List<User> users = new ArrayList<>();
        String name = textFieldName.getText();

        //If the user has not entered anything, the search will not be done
        if (name.equalsIgnoreCase("") || name.equalsIgnoreCase(" ")) {
            Log.info("No data to search in search user by name");

            if (observableList.size() != userList.size()) {//Ni se estan mostrando todos los usuarios por pantalla, se mostrarán ahora
                observableList.clear();
                observableList.addAll(userList);
            }

        } else {

            for (User user : userList) {
                if (user.getName().equalsIgnoreCase(name)) {
                    users.add(user);
                }
            }

            observableList.clear();
            observableList.addAll(users);
        }
    }

}
