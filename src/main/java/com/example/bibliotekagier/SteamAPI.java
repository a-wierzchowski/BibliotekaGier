package com.example.bibliotekagier;

import java.util.ArrayList;

public class SteamAPI {

    private final String  apiKey;
    private final String userLogin;
    private  String steamID;

    ArrayList listaGier = new ArrayList<String>();

    public SteamAPI(String apiKey, String userLogin){
        this.apiKey = apiKey;
        this.userLogin = userLogin;

        initSteamID();
        initListaGier();

    }

    private void initSteamID(){

    }

    private void initListaGier(){

    }

}
