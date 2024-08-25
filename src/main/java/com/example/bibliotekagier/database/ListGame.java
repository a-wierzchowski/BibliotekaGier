package com.example.bibliotekagier.database;

public class ListGame {
    private  String Title;
    private String Steam;
    private String GOG;
    private String Epic;
    private String PlayStation;
    private String Xbox;
    private String FizycznaKopia;
    private String Status;

    public ListGame() {
        Steam = "Nie";
        GOG = "Nie";
        Epic = "Nie";
        PlayStation = "Nie";
        Xbox = "Nie";
        FizycznaKopia = "Nie";
    }

    @Override
    public String toString() {
        return "ListGame{" +
                "Title='" + Title + '\'' +
                ", Steam=" + Steam +
                ", GOG=" + GOG +
                ", Epic=" + Epic +
                ", PlayStation=" + PlayStation +
                ", Xbox=" + Xbox +
                ", FizycznaKopia=" + FizycznaKopia +
                ", Status='" + Status +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public String getSteam() {
        return Steam;
    }

    public String getGOG() {
        return GOG;
    }

    public String getEpic() {
        return Epic;
    }

    public String getPlayStation() {
        return PlayStation;
    }

    public String getXbox() {
        return Xbox;
    }

    public String getFizycznaKopia() {
        return FizycznaKopia;
    }

    public String getStatus() {
        return Status;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSteam(String steam) {
        Steam = steam;
    }

    public void setGOG(String GOG) {
        this.GOG = GOG;
    }

    public void setEpic(String epic) {
        Epic = epic;
    }

    public void setPlayStation(String playStation) {
        PlayStation = playStation;
    }

    public void setXbox(String xbox) {
        Xbox = xbox;
    }

    public void setFizycznaKopia(String fizycznaKopia) {
        FizycznaKopia = fizycznaKopia;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
