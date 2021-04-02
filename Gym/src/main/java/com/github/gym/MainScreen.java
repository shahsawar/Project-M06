package com.github.gym;

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
 * @autor shah
 */
public class MainScreen implements Initializable {


    @FXML
    private ImageView imagenMainScreen;

    @FXML
    private Button mainScreenAddUserBtn;

    @FXML
    private TextField textFieldName;

    @FXML
    private Button btnFind;

    @FXML
    private TableView<User> mainScreenTable;

    @FXML
    private TableColumn<User, String> colUserDni;

    @FXML
    private TableColumn<User, String> colUserName;

    @FXML
    private TableColumn<User, String> colUserLastname;

    @FXML
    private TableColumn<User, String> colUserbirthdate;

    ObservableList<User> observableList = FXCollections.observableArrayList();
    List<User> userList = App.gestorPersistencia.getAllUsers();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image(getClass().getResourceAsStream("/images/settings.png"));
        imagenMainScreen.setImage(image);

        observableList.addAll(userList);

        colUserDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colUserbirthdate.setCellValueFactory(new FormattedDateValueFactory<User>("birthDate","dd/MM/yyyy"));

        mainScreenTable.setItems(observableList);
        mainScreenTable.setEditable(true);
        colUserName.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserbirthdate.setCellFactory(TextFieldTableCell.forTableColumn());

        //Roatation animation
        RotateTransition rt = new RotateTransition(Duration.millis(3000), imagenMainScreen);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);

        //Hover
        imagenMainScreen.setOnMouseEntered(e -> rt.play());
        imagenMainScreen.setOnMouseExited(e -> rt.pause());

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

    public void updateUserName(TableColumn.CellEditEvent<User, String> userStringCellEditEvent) {
        User user = mainScreenTable.getSelectionModel().getSelectedItem();
        user.setName(userStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateUser(user, user.getUserCode());
    }

    public void updateUserLastname(TableColumn.CellEditEvent<User, String> userStringCellEditEvent) {
        User user = mainScreenTable.getSelectionModel().getSelectedItem();
        user.setLastname(userStringCellEditEvent.getNewValue());
        App.gestorPersistencia.updateUser(user, user.getUserCode());
    }

    public void updateBirthdate(TableColumn.CellEditEvent<Reservation, String> reservationStringCellEditEvent) {
        User user = mainScreenTable.getSelectionModel().getSelectedItem();

        //String to date
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(reservationStringCellEditEvent.getNewValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthDate(date);
        App.gestorPersistencia.updateUser(user, user.getUserCode());
    }


    public void removeUser(ActionEvent actionEvent) {

        int selectedItems = mainScreenTable.getSelectionModel().getSelectedItems().size();

        if (selectedItems == 0){
            Log.warning("Not user selected, can't delete");

            //Monstramos la alerta
            Alert alert = new Alert(Alert.AlertType.NONE, "No user selected, can't delete", ButtonType.OK);
            alert.setTitle("No user selected");
            alert.showAndWait();

        } else {

            //User selected to be remove
            ObservableList<User> selectedUser = mainScreenTable.getSelectionModel().getSelectedItems();

            //Remove user from database
            User userTmp = App.gestorPersistencia.getUserById(selectedUser.get(0).getUserCode());
            App.gestorPersistencia.deleteUser(userTmp);

            Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to delete user " + userTmp.getName() + " with dni: " + userTmp.getDni(), ButtonType.NO, ButtonType.YES);
            alert.setTitle("Are you sure?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //Remove user from tableview
                selectedUser.forEach(observableList::remove);

                //Remove from the array
                userList.remove(userTmp);
            }
        }
    }


    @FXML
    private void addReservation(ActionEvent event){


        int selectedItems = mainScreenTable.getSelectionModel().getSelectedItems().size();

        if (selectedItems == 0){
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
            ReservationController r = loader.getController();
            r.setUserData(userSelected);
            Parent p = loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void searchUserByName(ActionEvent event){
        List<User> users = new ArrayList<>();
        String name = textFieldName.getText();

        //Si el usuario no ha escrito nada, no se hará la búsqueda
        if (name.equalsIgnoreCase("") || name.equalsIgnoreCase(" ") ){
            Log.info("No data to search in search user by name");

            if (observableList.size() != userList.size()){//Ni se estan mostrando todos los usuarios por pantalla, se mostrarán ahora
                observableList.clear();
                observableList.addAll(userList);
            }

        } else {

            for (User user : userList){
                if (user.getName().equalsIgnoreCase(name)){
                    users.add(user);
                }
            }

            observableList.clear();
            observableList.addAll(users);
        }
    }

}
