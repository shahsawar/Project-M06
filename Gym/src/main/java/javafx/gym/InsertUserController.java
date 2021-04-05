package javafx.gym;

import clases.User;
import exceptions.DatabaseNotAvailableExecption;
import exceptions.KeyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.DataValidator;
import utilities.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

        boolean canInsertUser = true;
        Date date = new Date();

        if (dateInput.getValue() == null || nameInput.getText().length() == 0 || lastnameInput.getText().length() == 0 || lastnameInput.getText().length() == 0){//Por si la fecha está vacía
            canInsertUser = false;
            showPopUp("Please fill all the fields");
        } else {
            LocalDate localDate = dateInput.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date = Date.from(instant);

            if (!DataValidator.isDNICorrect(dniInput.getText())){
                showPopUp("DNI is incorrect");
                canInsertUser = false;

            } else if (!DataValidator.isName_LastNameCorrect(nameInput.getText())){
                showPopUp("Name is incorrect");
                canInsertUser = false;
            } else if (!DataValidator.isName_LastNameCorrect(lastnameInput.getText())){
                showPopUp("Lastname is incorrect");
                canInsertUser = false;
            }
        }

        if (canInsertUser){

            try {

                App.gestorPersistencia.insertUser(new User(dniInput.getText(), nameInput.getText(), lastnameInput.getText(), date));
                try {
                    App.setRoot("MainScreen");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (KeyException e){ //If is KeyException we show a popUp
                String message = "User with dni '" + dniInput.getText() + "' already exists";
                showPopUp(message);
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                Log.severe(message + "\n" + sw.toString());
                System.out.println(message);

            } catch (DatabaseNotAvailableExecption ex) {
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                Log.severe("\nCould not insert the user in database!\n" + sw.toString());
                System.out.println("Could not insert the user in database!");
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
