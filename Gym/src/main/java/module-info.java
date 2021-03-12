module com.github.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires java.sql;

    opens com.github.gym to javafx.fxml;
    exports com.github.gym;
}