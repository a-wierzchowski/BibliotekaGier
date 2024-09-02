package com.example.bibliotekagier;

import com.example.bibliotekagier.controllers.*;
import com.example.bibliotekagier.database.Database;
import com.example.bibliotekagier.database.Platformy;
import com.example.bibliotekagier.database.Profil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label exit;
    @FXML
    private StackPane contentArea;

    private Database database;
    private ProfileController profileController;
    private ListGameController listGameController;
    private RateController rateController;
    private AddGameController addGameController;
    private AddToLibraryController addToLibraryController;
    private List<Profil> profile;
    private List<Platformy> platformy;
    @FXML
    private Label labelWelcomeProfile;

    @FXML
    public void rate(ActionEvent actionEvent) throws IOException {
        setupLabelWelcomeProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/rate.fxml"));

        int index = getIndexProfile();

        if (index == -1){
            profileError();
        }
        else {

            loader.setControllerFactory(param -> {
                rateController = new RateController(database, index);
                return rateController;
            });

            Parent fxml = loader.load();
            rateController.setRoot(fxml);

            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(rateController.getRoot());
        }
    }

    @FXML
    public void addGame(ActionEvent actionEvent) throws IOException {
        setupLabelWelcomeProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/addGame.fxml"));

        loader.setControllerFactory(param -> {
            addGameController = new AddGameController(database);
            return addGameController;
        });

        Parent fxml = loader.load();
        addGameController.setRoot(fxml);

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(addGameController.getRoot());

    }
    @FXML
    public void addToLibrary(ActionEvent actionEvent) throws IOException {
        setupLabelWelcomeProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/addToLibrary.fxml"));

        int index = getIndexProfile();

        if (index == -1){
            profileError();
        }
        else {

            loader.setControllerFactory(param -> {
                addToLibraryController = new AddToLibraryController(database, platformy, index);
                return addToLibraryController;
            });

            Parent fxml = loader.load();
            addToLibraryController.setRoot(fxml);

            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(addToLibraryController.getRoot());
        }
    }

    private void profileError() throws IOException {
        setupLabelWelcomeProfile();
        System.err.println("Brak wybranego profilu");
        Parent fxml = FXMLLoader.load(getClass().getResource("scene/profileError.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void profile(ActionEvent actionEvent) throws IOException {
        labelWelcomeProfile.setText("");
        if (profileController == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/profile.fxml"));

            loader.setControllerFactory(param -> {
                profileController = new ProfileController(database, profile);
                return profileController;
            });

            Parent fxml = loader.load();
            profileController.setRoot(fxml);
        }

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(profileController.getRoot());
    }

    @FXML
    public void listGame(ActionEvent actionEvent) throws IOException {
        setupLabelWelcomeProfile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/listGame.fxml"));
        int index = getIndexProfile();

        if (index == -1){
            profileError();
        }
        else {
            loader.setControllerFactory(param -> {
                listGameController = new ListGameController(database, profile, index);
                return listGameController;
            });

            Parent fxml = loader.load();
            listGameController.setRoot(fxml);

            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(listGameController.getRoot());
        }

    }

    private int getIndexProfile() {
        if (profileController != null){
            int index = profileController.getIndexListViewProfile();
            if (index != -1){
                Profil aktProfil = profile.get(index);
                if (!aktProfil.getNazwa_profil().equals("____BRAK____")){
                    return index;
                }
            }
        }
        return -1;
    }
    private void setupLabelWelcomeProfile(){
        profile = database.getProfile();
        int index = getIndexProfile();
        if ( index != -1){
            String profleName = profile.get(index).getNazwa_profil();
            labelWelcomeProfile.setText("Witaj: " + profleName);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Obsługa braku połączenia z bazą danych
        boolean connected = false;
        while (!connected){
            try {
                database = new Database();
                connected = true;
            } catch (Exception e){
                System.out.println("Błąd połączenia, ponowna próba połączenia");
                for (int i = 0; i < 20; i++) {
                    System.out.printf("#");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println();
            }
        }

        profile = database.getProfile();
        platformy = database.getPlatformy();

        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

    }


}