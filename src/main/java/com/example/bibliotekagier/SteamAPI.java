package com.example.bibliotekagier;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.getschemaforgame.GetSchemaForGame;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetSchemaForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SteamAPI {

    private final String  apiKey;
    private final String userLogin;
    private  String steamID;

    class Game {
        String name;
        Long appID;
        String icon;
        Long playTime;
    }

    ArrayList<SteamAPI.Game> listaGier = new ArrayList<>();

    public SteamAPI(String apiKey, String userLogin){
        this.apiKey = apiKey;
        this.userLogin = userLogin;

        initSteamID();
        initListaGier();
    }

    private JSONObject getJSON(URL url){
        // Tworze polaczenie
        HttpURLConnection conn = null;
        String line = "";
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Sprawdzam poczlaczenie
            int responseCode = conn.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            // Pobieranie danych
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                line += scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Tworzenie JSON z danych;
        JSONObject json = new JSONObject(line);

        return json;
    }

    private void initSteamID(){
        URL urlApiSteamID = null;
        try {
            urlApiSteamID = new URL("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + apiKey + "&vanityurl=" + userLogin  );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        JSONObject json = getJSON(urlApiSteamID);
        steamID = json.optJSONObject("response").optString("steamid");
    }



    private URL urlApiLogoGame(Long appID, String logoHash){
        URL url = null;
        try {
            url = new URL("http://media.steampowered.com/steamcommunity/public/images/apps/"+ appID +"/"+ logoHash +".jpg");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public void printListaGier(){
        for (Game g : listaGier){
            System.out.println(g.name);
        }
    }

    public ArrayList getListaGier() {
        return listaGier;
    }
    private void initListaGier(){
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();

        GetOwnedGamesRequest req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder(steamID).includeAppInfo(true).buildRequest();
        GetOwnedGames getOwnedGames = null;
        try {
            getOwnedGames = client.<GetOwnedGames> processRequest(req);
        } catch (SteamApiException e) {
            throw new RuntimeException(e);
        }

        for ( com.lukaspradel.steamapi.data.json.ownedgames.Game game : getOwnedGames.getResponse().getGames()){
            SteamAPI.Game gra = new Game();
            gra.name = game.getName();
            gra.appID = game.getAppid();
            gra.icon = game.getImgIconUrl();
            gra.playTime = game.getPlaytimeForever();

            listaGier.add(gra);
        }
    }

    public void steamGameInfo(Long appID){
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
        GetSchemaForGameRequest req = SteamWebApiRequestFactory.createGetSchemaForGameRequest(Integer.valueOf(steamID));
        GetSchemaForGame game = null;
        try {
            game = client.<GetSchemaForGame> processRequest(req);
        } catch (SteamApiException e) {
            throw new RuntimeException(e);
        }

        System.out.println(game.getGame());

    }

}
