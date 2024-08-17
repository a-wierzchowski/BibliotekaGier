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
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private Database database;
    private List<Profil> profile;
    private Parent root;
    private ProfileEditController profileEditController;

    private Integer indexListViewProfile;

    @FXML
    private ListView listViewProfile;

    @FXML
    private Label labelWybierzProfil;
    @FXML
    private StackPane contentAreaProfile;

    public ProfileController(Database database, List<Profil> profile) {
        this.database = database;
        this.profile = profile;
        indexListViewProfile = -1;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }
    public int getIndexListViewProfile() {
        return indexListViewProfile;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> nazwa_profil = new ArrayList<>(15);

        String temp;
        for (int i = 0; i < 15; i++) {
            temp = profile.get(i).getNazwa_profil();
            nazwa_profil.add(temp);
        }

        ObservableList<String> observableProfilName = FXCollections.observableList(nazwa_profil);
        listViewProfile.setItems(observableProfilName);
        if (indexListViewProfile != -1){
            setupLabelWybierzProfil();
        }

    }

    @FXML
    public void eventMouseClicked(Event event) {
        indexListViewProfile = listViewProfile.getSelectionModel().getSelectedIndex();
        setupLabelWybierzProfil();
    }

    private void setupLabelWybierzProfil(){
        String profil = (String) listViewProfile.getItems().get(indexListViewProfile);
        labelWybierzProfil.setText("Wybrany profil: " + profil);
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        /*
        if (profileEditController == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/profileEdit.fxml"));

            loader.setControllerFactory(param -> {
                profileEditController = new ProfileEditController(database);
                return profileEditController;
            });

            Parent fxmlExit = loader.load();
            profileEditController.setRoot(fxmlExit);
        }

        contentAreaProfile.getChildren().removeAll();
        contentAreaProfile.getChildren().setAll(profileEditController.getRoot());
         */


        Parent fxml = FXMLLoader.load(this.getClass().getResource("profileEdit.fxml"));
        contentAreaProfile.getChildren().removeAll();
        contentAreaProfile.getChildren().setAll(fxml);




    }
}