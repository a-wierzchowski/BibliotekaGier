package com.example.bibliotekagier;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
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
    public static void main(String[] args) throws SteamApiException {
        //launch();

        /*SteamAPI mainSteamAPI;
        try {
            mainSteamAPI = new SteamAPI("CFD041580B9722BEE17C8657A6A8CC1B", "pixelekpl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("CFD041580B9722BEE17C8657A6A8CC1B").build();

        GetOwnedGamesRequest req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder("76561198200147585").includeAppInfo(true).buildRequest();
        GetOwnedGames getOwnedGames = client.<GetOwnedGames> processRequest(req);

        for ( com.lukaspradel.steamapi.data.json.ownedgames.Game game : getOwnedGames.getResponse().getGames()){
            System.out.println(game.getName());
        }

    }


}