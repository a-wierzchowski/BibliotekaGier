package com.example.bibliotekagier;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    double x,y;
    @Override
    public void start (Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws SteamApiException {

        launch();

    /*
        SteamAPI mainSteamAPI;
        mainSteamAPI = new SteamAPI("CFD041580B9722BEE17C8657A6A8CC1B", "pixelekpl");
        //mainSteamAPI.printListaGier();
        mainSteamAPI.steamGameInfo(10L);
    */

    }

}