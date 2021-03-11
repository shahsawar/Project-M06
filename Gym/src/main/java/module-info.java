module com.github.gym {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.gym to javafx.fxml;
    exports com.github.gym;
}