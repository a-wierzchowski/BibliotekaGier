package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Gry;
import com.example.bibliotekagier.database.Platformy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

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
    private ComboBox comboBoxPlatformy;
    @javafx.fxml.FXML
    private ComboBox comboBoxStatus;
    @FXML
    private TextField textFieldFindTitle;
    @FXML
    private ListView listViewListaTitle;
    @FXML
    private Label labelResult;

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
        textFieldFindTitleAction(null);
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
    @FXML
    public void textFieldFindTitleAction(Event event) {
        String title = textFieldFindTitle.getText().toUpperCase();
        List<Gry> query;
        if (title.isEmpty()) {
            query = database.getGry();
        }
        else{
            query = database.findTitleGame(title);
        }
        List<String> listaTitle = new ArrayList<>();

        String temp;

        for (Gry g : query) {
            temp = g.getTytul_gry();
            listaTitle.add(temp);
        }

        ObservableList<String> observablePlatformName = FXCollections.observableList(listaTitle);
        listViewListaTitle.setItems(observablePlatformName);
    }

    @FXML
    public void buttonAddToLibrary(ActionEvent actionEvent) {
        String title = "";
        ObservableList temp = listViewListaTitle.getSelectionModel().getSelectedItems();
        if (!temp.isEmpty()){
            title = temp.get(0).toString();
        }
        String platforma = "";
        if (comboBoxPlatformy.getValue() != null){
            platforma = comboBoxPlatformy.getValue().toString();
        }
        String status = "";
        if (comboBoxStatus.getValue() != null){
            status = comboBoxStatus.getValue().toString();
        }

        try {
            database.addGameToLibrary(title, platforma, status, (long) indexListViewProfile+1);
        }
        catch (IllegalArgumentException e){
            labelResult.setText(e.getMessage());
            return;
        }
        labelResult.setText("Gra '" + title + "' została dodana do Biblioteki");

    }
}