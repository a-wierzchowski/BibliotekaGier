module com.example.bibliotekagier {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    requires org.controlsfx.controls;

    opens com.example.bibliotekagier to javafx.fxml;
    exports com.example.bibliotekagier;
}