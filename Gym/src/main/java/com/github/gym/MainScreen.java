package com.github.gym;

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
import java.util.List;
import java.util.ResourceBundle;

import clases.*;
import javafx.stage.Stage;

/**
 * @autor shah
 */
public class MainScreen implements Initializable {


    @FXML
    private ImageView imagenMainScreen;

    @FXML
    private Button mainScreenAddReservation;

    @FXML
    private TableView<User> mainScreenTable;

    @FXML
    private TableColumn<User, String> colUserDni;

    @FXML
    private TableColumn<User, String> colUserName;

    @FXML
    private TableColumn<User, String> colUserLastname;

    ObservableList<User> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image(getClass().getResourceAsStream("/images/settings.png"));
        imagenMainScreen.setImage(image);

        List<User> userList = App.gestorPersistencia.getAllUsers();
        observableList.addAll(userList);

        colUserDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        mainScreenTable.setItems(observableList);
        mainScreenTable.setEditable(true);
        colUserName.setCellFactory(TextFieldTableCell.forTableColumn());
        colUserLastname.setCellFactory(TextFieldTableCell.forTableColumn());

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

        mainScreenAddReservation.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    App.setRootWithData("reservation", mainScreenTable.getSelectionModel().getSelectedItems().get(0));
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

    public void removeUser(ActionEvent actionEvent) {

        //User selected to be remove
        ObservableList<User> selectedUser = mainScreenTable.getSelectionModel().getSelectedItems();

        //Remove user from database
        User userTmp = App.gestorPersistencia.getUserById(selectedUser.get(0).getUserCode());
        App.gestorPersistencia.deleteUser(userTmp);

        //Remove user from tableview
        selectedUser.forEach(observableList::remove);
    }


    @FXML
    private void handleButtonAction(ActionEvent event){
        User u = mainScreenTable.getSelectionModel().getSelectedItems().get(0);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reservation.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReservationController r = loader.getController();
        r.setUserData(mainScreenTable.getSelectionModel().getSelectedItems().get(0));
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
    }

}
