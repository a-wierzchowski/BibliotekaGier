package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Platformy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddToLibraryController implements Initializable {

    private Database database;
    private Parent root;
    private List<Platformy> platformy;
    private SteamAPI steamAPI;

    private int indexListViewProfile;
    @javafx.fxml.FXML
    private ComboBox comboBoxTitle;
    @javafx.fxml.FXML
    private ComboBox comboBoxPlatformy;
    @javafx.fxml.FXML
    private ComboBox comboBoxStatus;
    @javafx.fxml.FXML
    private SplitMenuButton SplitMenuButtonTitle;

    public AddToLibraryController(Database database, List<Platformy> platformy, int index) {
        this.database = database;
        this.platformy = platformy;
        indexListViewProfile = index;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupComboBoxStatus();
        setupComboBoxPlatformy();
        setupComboBoxTitle();
    }

    private void setupComboBoxPlatformy(){

        List<String> nazwa_platformy  = new ArrayList<>();

        String temp;

        for (Platformy p : platformy) {
            temp = p.getNazwa_platformy();
            nazwa_platformy.add(temp);
        }

        ObservableList<String> observablePlatformName = FXCollections.observableList(nazwa_platformy);
        comboBoxPlatformy.setItems(observablePlatformName);

    }
    private void setupComboBoxStatus(){
        List<String> listaStatus = new ArrayList<>();

        listaStatus.add("Nowa");
        listaStatus.add("W trakcie");
        listaStatus.add("Ukończona");

        ObservableList<String> observableStatusList = FXCollections.observableList(listaStatus);
        comboBoxStatus.setItems(observableStatusList);
    }

    private void setupComboBoxTitle(){
        List<String> nazwa_platformy  = new ArrayList<>();

        String temp;

        for (Platformy p : platformy) {
            temp = p.getNazwa_platformy();
            nazwa_platformy.add(temp);
        }

        ObservableList<String> observablePlatformName = FXCollections.observableList(nazwa_platformy);
        comboBoxTitle.setItems(observablePlatformName);
    }
}