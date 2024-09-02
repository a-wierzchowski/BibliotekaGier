package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
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
    private TextField profileName;
    @FXML
    private TextField profileSteamApi;
    @FXML
    private TextField profileUserLogin;
    @FXML
    private AnchorPane contentAreaProfileEdit;
    @FXML
    private AnchorPane contentAreaProfileDelete;
    @FXML
    private Button nie;
    @FXML
    private Button tak;

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

        setupListViewProfile();

        contentAreaProfileEdit.setVisible(false);
        contentAreaProfileDelete.setVisible(false);

    }

    private void setupSteamApi() {
        Profil profil = profile.get(indexListViewProfile);
        String apiKey = profil.getSteamapikey();
        String login = profil.getSteamuserlogin();
        SteamAPI steamAPI = new SteamAPI(apiKey, login);

    }


    private void setupListViewProfile(){

        profile = database.getProfile();

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
        try {
            setupSteamApi();
        }catch (Exception e){
            System.err.println("Błąd połączenia z SteamAPI");
        }
    }

    private void setupLabelWybierzProfil(){
        String profil = (String) listViewProfile.getItems().get(indexListViewProfile);
        labelWybierzProfil.setText("Wybrany profil: " + profil);
    }

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        if (indexListViewProfile == -1){
            labelWybierzProfil.setText("Nie wybrano żadnego profilu");
        }
        else {
            if (contentAreaProfileEdit.isVisible()){
                contentAreaProfileEdit.setVisible(false);
            }
            else {
                contentAreaProfileEdit.setVisible(true);
                contentAreaProfileDelete.setVisible(false);
            }
        }
    }

    @FXML
    public void saveEditProfile(ActionEvent actionEvent) {

        if (indexListViewProfile == -1){
            labelWybierzProfil.setText("Aby edytować, wybierz profil");
        }
        else {
            String name = profileName.getText();
            String apiKey = profileSteamApi.getText();
            String login = profileUserLogin.getText();
            Profil profil = profile.get(indexListViewProfile);
            Long index = profil.getId_profilu();

            if (name.equals("____BRAK____") || name.isEmpty()){
                labelWybierzProfil.setText("Nie można nadać takiej nazwy");
            }
            else {
                if (apiKey.isEmpty()){
                    apiKey = ("____BRAK____");
                }
                if (login.isEmpty()){
                    login = ("____BRAK____");
                }
                setupLabelWybierzProfil();
                database.updateProfile(index, name, apiKey, login);
                setupListViewProfile();
                contentAreaProfileEdit.setVisible(false);
            }
        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        if (indexListViewProfile == -1){
            labelWybierzProfil.setText("Nie wybrano żadnego profilu");
        }
        else {
            if (contentAreaProfileDelete.isVisible()){
                contentAreaProfileDelete.setVisible(false);
            }
            else {
                contentAreaProfileEdit.setVisible(false);
                contentAreaProfileDelete.setVisible(true);
            }
        }
    }

    @FXML
    public void deleteChoice(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        if (buttonId.equals("tak")){
            Long index = profile.get(indexListViewProfile).getId_profilu();

            System.out.println(index);

            String remove = "____BRAK____";
            database.updateProfile(index, remove, remove, remove);
            database.deleteOcenyGier(index);
            database.deletePosiadane(index);
            setupListViewProfile();
        }

        contentAreaProfileDelete.setVisible(false);
    }
}