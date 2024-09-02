package com.example.bibliotekagier.database;

public class OcenyGierView {
    private  String Title;
    private double SrOcena;
    private int Ocena;
    private String Komentarz;

    public void setTitle(String title) {
        Title = title;
    }

    public void setSrOcena(double srOcena) {
        SrOcena = srOcena;
    }

    public void setOcena(int ocena) {
        Ocena = ocena;
    }

    public void setKomentarz(String komentarz) {
        Komentarz = komentarz;
    }

    public String getTitle() {
        return Title;
    }

    public double getSrOcena() {
        return SrOcena;
    }

    public int getOcena() {
        return Ocena;
    }

    public String getKomentarz() {
        return Komentarz;
    }
}
