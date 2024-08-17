package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Profil;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListGameController implements Initializable {

    private Database database;
    private Parent root;
    private List<Profil> profile;
    private SteamAPI steamAPI;

    private int indexListViewProfile;

    public ListGameController(Database database, List<Profil> profile, int index) {
        this.database = database;
        this.profile = profile;
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

        if (indexListViewProfile != -1){

            Profil aktProfil = profile.get(indexListViewProfile);
                System.out.println("Akt profil: " + indexListViewProfile + ". " + aktProfil.getNazwa_profil());
                //steamAPI = new SteamAPI(aktProfil.getSteamapikey(), aktProfil.getSteamuserlogin());
                //steamAPI.printListaGier();
        }
        else {
            System.out.println("Brak wybranego profilu");
        }

    }

}