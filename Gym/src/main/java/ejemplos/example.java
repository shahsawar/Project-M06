package ejemplos;

import clases.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @autor shah
 */
public class example {

    //Pasar datos entre actividades
    /*
    //Scene1
    @FXML
    private void sendData(MouseEvent event) {
        // Step 1
        User u = mainScreenTable.getSelectionModel().getSelectedItems().get(0);
        // Step 2
        Node node = (Node) event.getSource();
        // Step 3
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            // Step 4
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScreen.fxml"));
            // Step 5
            stage.setUserData(u);
            // Step 6
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Step 7
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    //Scene2
    @FXML
    private void receiveData(MouseEvent event) {
        // Step 1
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        // Step 2
        User u = (User) stage.getUserData();
        // Step 3
        reservationUserDni.setText(u.getDni());
        reservationUserName.setText(u.getName());
        reservationUserLastname.setText(u.getLastname());
    }*/

    /*
        mainScreenAddBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    App.setRoot("insertUser");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
}
