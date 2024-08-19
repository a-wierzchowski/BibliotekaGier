package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Gry;
import com.example.bibliotekagier.database.Platformy;
import com.example.bibliotekagier.database.Profil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AddGameController implements Initializable {

    private Database database;
    private Parent root;
    private SteamAPI steamAPI;

    private int indexListViewProfile;
    @javafx.fxml.FXML
    private TextField textFieldTitle;
    @javafx.fxml.FXML
    private TextField textFieldSteamID;
    @FXML
    private Label labelResult;
    @FXML
    private ListView listViewListaTitle;
    @FXML
    private TextField textFieldFindTitle;

    public AddGameController(Database database) {
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

        // tylko liczby
        textFieldSteamID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldSteamID.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }
    @FXML
    public void buttonAdd(ActionEvent actionEvent) {
        String title = textFieldTitle.getText().toUpperCase();
        String tempSteamID = textFieldSteamID.getText();
        Long steamID = !tempSteamID.isEmpty() ? Long.valueOf(tempSteamID) : null;

        if (!title.isEmpty()){
            try {
                database.addGame(title, steamID);
            }
            catch (IllegalArgumentException e){
                labelResult.setText(e.getMessage());
                return;
            }
            labelResult.setText("Gra została dodana do bazy");
        }
    }

    @FXML
    public void textFieldFindTitleAction(Event event) {
        String title = textFieldFindTitle.getText().toUpperCase();
        if (title.isEmpty()) {
            return;
        }
        List<Gry> query = database.findTitleGame(title);
        List<String> listaTitle = new ArrayList<>();

        String temp;

        for (Gry g : query) {
            temp = g.getTytul_gry();
            listaTitle.add(temp);
        }

        ObservableList<String> observablePlatformName = FXCollections.observableList(listaTitle);
        listViewListaTitle.setItems(observablePlatformName);
    }
}
