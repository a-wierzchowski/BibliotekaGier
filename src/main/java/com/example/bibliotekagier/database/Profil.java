package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profil")
public class Profil {
    @Id
    @GeneratedValue
    private Long id_profilu;
    private String nazwa_profil;
    private String steamapikey;
    private String steamuserlogin;
    private Date data_rejestracji;
    private Integer calkowita_liczba_godzin;

    @Override
    public String toString() {
        return "Profil{" +
                "id_profilu=" + id_profilu +
                ", nazwa_profil='" + nazwa_profil + '\'' +
                ", steamapikey='" + steamapikey + '\'' +
                ", steamuserlogin='" + steamuserlogin + '\'' +
                ", data_rejestracji='" + data_rejestracji + '\'' +
                ", calkowita_liczba_godzin=" + calkowita_liczba_godzin +
                '}';
    }

    public Long getId_profilu() {
        return id_profilu;
    }

    public String getNazwa_profil() {
        return nazwa_profil;
    }

    public String getSteamapikey() {
        return steamapikey;
    }

    public String getSteamuserlogin() {
        return steamuserlogin;
    }

    public Date getData_rejestracji() {
        return data_rejestracji;
    }

    public Integer getCalkowita_liczba_godzin() {
        return calkowita_liczba_godzin;
    }

    public void setId_profilu(Long id_profilu) {
        this.id_profilu = id_profilu;
    }

    public void setNazwa_profil(String nazwa_profil) {
        this.nazwa_profil = nazwa_profil;
    }

    public void setSteamapikey(String steamapikey) {
        this.steamapikey = steamapikey;
    }

    public void setSteamuserlogin(String steamuserlogin) {
        this.steamuserlogin = steamuserlogin;
    }

    public void setData_rejestracji(Date data_rejestracji) {
        this.data_rejestracji = data_rejestracji;
    }

    public void setCalkowita_liczba_godzin(Integer calkowita_liczba_godzin) {
        this.calkowita_liczba_godzin = calkowita_liczba_godzin;
    }
}
