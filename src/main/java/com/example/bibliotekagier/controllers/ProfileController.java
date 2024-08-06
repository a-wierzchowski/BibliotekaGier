package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Profil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ListView listViewProfile;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Profil> p = database.getProfile();
        List<String> nazwa_profil = new ArrayList<>(15);

        //for (Profil x : p){
        //    nazwa_profil.add(x.getNazwa_profil());
        //}

        String temp;
        for (int i = 0; i < 15; i++) {
            if (i > p.size()-1)
                temp = "____BRAK____";
            else
                temp = p.get(i).getNazwa_profil();
            nazwa_profil.add(temp);
        }

        ObservableList<String> observableProfilName = FXCollections.observableList(nazwa_profil);
        listViewProfile.setItems(observableProfilName);

    }
}