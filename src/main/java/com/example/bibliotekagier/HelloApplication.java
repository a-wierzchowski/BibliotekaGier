package com.example.bibliotekagier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication{ /* extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    */
    public static void main(String[] args) {
        //launch();

        SteamAPI mainSteamAPI;
        try {
            mainSteamAPI = new SteamAPI("CFD041580B9722BEE17C8657A6A8CC1B", "pixelekpl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}