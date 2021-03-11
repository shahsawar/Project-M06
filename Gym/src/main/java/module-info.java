module com.github.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens com.github.gym to javafx.fxml;
    exports com.github.gym;
}