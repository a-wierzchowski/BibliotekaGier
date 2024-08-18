package com.example.bibliotekagier.database;

import javax.persistence.*;

@Entity
@Table(name = "gry")
public class Gry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_gry;
    private String tytul_gry;
    private Long steamappid;

    public void setId_gry(Long id_gry) {
        this.id_gry = id_gry;
    }

    public void setTytul_gry(String tytul_gry) {
        this.tytul_gry = tytul_gry;
    }

    public void setSteamappid(Long steamappid) {
        this.steamappid = steamappid;
    }

    public Long getId_gry() {
        return id_gry;
    }

    public String getTytul_gry() {
        return tytul_gry;
    }

    public Long getSteamappid() {
        return steamappid;
    }
}
