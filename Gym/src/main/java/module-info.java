module com.github.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires java.sql;

    opens com.github.gym to javafx.fxml;
    opens clases to javafx.base;

    exports com.github.gym;
}