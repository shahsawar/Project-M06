package javafx.gym;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    @FXML
    VBox mainVBox;


    @FXML
    private void switchToSecondary() throws IOException {
        //App.setRoot("secondary");
        //App.setRoot("configuration");
        App.setRoot("MainScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainVBox.getStyleClass().add("mainBox");
    }
}
