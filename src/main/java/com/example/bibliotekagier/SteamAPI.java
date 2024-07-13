package com.example.bibliotekagier;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class SteamAPI {

    private String apiKey;
    private String userLogin;
    private String steamID;

    ArrayList listaGier = new ArrayList<String>();

    public SteamAPI(String apiKey, String userLogin) throws IOException {
        this.apiKey = apiKey;
        this.userLogin = userLogin;

        initSteamID();
        initListaGier();

    }

    private JSONObject getJSON(URL url) throws IOException {
        // Tworze polaczenie
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Sprawdzam poczlaczenie
        int responseCode = conn.getResponseCode();
        if (responseCode != 200){
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        // Pobieranie danych
        String line = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()){
            line += scanner.nextLine();
        }
        scanner.close();

        // Tworzenie JSON z danych;
        JSONObject json = new JSONObject(line);

        return json;
    }

    private void initSteamID() throws IOException {
        URL urlApiSteamID = new URL("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + apiKey + "&vanityurl=" + userLogin  );

        JSONObject json = getJSON(urlApiSteamID);
        steamID = json.optJSONObject("response").optString("steamid");

    }

    private void initListaGier() throws IOException {
        URL urlAPIListaGier = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + apiKey + "&steamid="+ steamID +"&format=json");



        //#####################################################TO BE CONTINUE##########################


        JSONObject json = getJSON(urlAPIListaGier);
        JSONObject gry = json.optJSONObject("response").optJSONObject("games"); // do rozkminienia
        System.out.println(gry.toString());
    }

    @Override
    public String toString() {
        return "SteamAPI{" +
                "apiKey='" + apiKey + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", steamID='" + steamID + '\'' +
                '}';
    }
}
