package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "id_profilu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Posiadane> posiadane = new HashSet<>();
    @OneToMany(mappedBy = "id_profilu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OcenyGier> ocenyGier = new HashSet<>();

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

    public void setPosiadane(Set<Posiadane> posiadane) {
        this.posiadane = posiadane;
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

    public Set<Posiadane> getPosiadane() {
        return posiadane;
    }
}
