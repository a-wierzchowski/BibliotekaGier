package com.example.bibliotekagier;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class SteamAPI {

    private final String  apiKey;
    private final String userLogin;
    private  String steamID;

    ArrayList listaGier = new ArrayList<String>();

    public SteamAPI(String apiKey, String userLogin) throws IOException, InterruptedException {
        this.apiKey = apiKey;
        this.userLogin = userLogin;

        initSteamID();
        initListaGier();

    }

    private JSONObject getJSON(URL url) throws IOException, InterruptedException {
        // Tworze polaczenie
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Sprawdzam poczlaczenie
       /* int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            System.out.println(url);
            System.out.println(responseCode);
            while ( responseCode == 429) {
                try {
                    Thread.sleep(155400);
                    conn.connect();
                    responseCode = conn.getResponseCode();
                    System.out.println(responseCode);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }*/
        int responseCode;

        if ( (responseCode = conn.getResponseCode()) == 429 ){
            System.out.println("1. " + responseCode);

            int counter = 0;
            while (responseCode == 429){
                System.out.println("Try number: " + counter++ + " Code: " + responseCode);
                Thread.sleep(30000);
                conn.connect();
                responseCode = conn.getResponseCode();
            }
        }
        System.out.println("2. " + responseCode);

        // Pobieranie danych
        String line = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();

        // Tworzenie JSON z danych;
        JSONObject json = new JSONObject(line);

        return json;
    }

    private void initSteamID() throws IOException, InterruptedException {
        URL urlApiSteamID = new URL("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key="
                + apiKey + "&vanityurl=" + userLogin  );

        JSONObject json = getJSON(urlApiSteamID);
        steamID = json.optJSONObject("response").optString("steamid");

    }

    private void initListaGier() throws IOException, InterruptedException {
        URL urlAPIListaGier = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="
                + apiKey + "&steamid="+ steamID +"&format=json");

        JSONObject json = getJSON(urlAPIListaGier);
        JSONArray gryArray = json.optJSONObject("response").optJSONArray("games"); // do rozkminienia
        for(int i=0; i<gryArray.length(); i++){

            // zapezpieczenie by nie robić dos dla valve
           /* try {
                if ( i != 0 && i % 100 == 0)
                    Thread.sleep(10000);
                else if ( i != 0 && i % 2 == 0){
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            

            json = gryArray.getJSONObject(i);
            int appid = json.getInt("appid");

            URL urlAPIDaneGry = new URL("https://store.steampowered.com/api/appdetails?appids=" + appid);
            JSONObject jsonGry = getJSON(urlAPIDaneGry);

            if(jsonGry.optJSONObject(String.valueOf(appid)).optJSONObject("data") != null) {
                String name = jsonGry.optJSONObject(String.valueOf(appid)).optJSONObject("data").optString("name");
                System.out.println( name);
            }


        }


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
