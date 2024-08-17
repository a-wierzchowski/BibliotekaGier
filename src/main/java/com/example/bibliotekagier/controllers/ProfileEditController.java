package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Profil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileEditController implements Initializable {

    private Database database;
    private Parent root;

    @FXML
    private TextField profileName;
    @FXML
    private TextField profileSteamApi;
    @FXML
    private TextField profileUserLogin;

    public ProfileEditController(Database database) {
        this.database = database;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}