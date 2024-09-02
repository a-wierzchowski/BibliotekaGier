package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "oceny_gier")
public class OcenyGier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_oceny;
    @ManyToOne
    @JoinColumn(name = "id_profilu")
    private Profil id_profilu;

    @ManyToOne
    @JoinColumn(name = "id_gry")
    private Gry id_gry;

    private int ocena_uzytkownika;
    private String komentarz;
    private Date data_wystawienia_oceny;

    public void setId_oceny(Long id_oceny) {
        this.id_oceny = id_oceny;
    }

    public void setId_profilu(Profil id_profilu) {
        this.id_profilu = id_profilu;
    }

    public void setId_gry(Gry id_gry) {
        this.id_gry = id_gry;
    }

    public void setOcena_uzytkownika(int ocena_uzytkownika) {
        this.ocena_uzytkownika = ocena_uzytkownika;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }

    public void setData_wystawienia_oceny(Date data_wystawienia_oceny) {
        this.data_wystawienia_oceny = data_wystawienia_oceny;
    }

    public Long getId_oceny() {
        return id_oceny;
    }

    public Profil getId_profilu() {
        return id_profilu;
    }

    public Gry getId_gry() {
        return id_gry;
    }

    public int getOcena_uzytkownika() {
        return ocena_uzytkownika;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public Date getData_wystawienia_oceny() {
        return data_wystawienia_oceny;
    }
}
