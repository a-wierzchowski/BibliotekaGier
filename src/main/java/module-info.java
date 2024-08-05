module com.example.bibliotekagier {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.lukaspradel.steamapi;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    requires org.controlsfx.controls;
    requires java.persistence;

    opens com.example.bibliotekagier to javafx.fxml;
    exports com.example.bibliotekagier;
    exports com.example.bibliotekagier.database;
    opens com.example.bibliotekagier.database to javafx.fxml, org.hibernate.orm.core;

}