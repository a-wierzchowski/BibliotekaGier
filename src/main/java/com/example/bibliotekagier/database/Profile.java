package com.example.bibliotekagier.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfilu;

    private String nazwaProfil;
    private String steamApiKey;
    private String steamUserLogin;
    private Date dataRejestracji;
    private Integer calkowitaLiczbaGodzin;

    // Getters and setters
    public Long getIdProfilu() { return idProfilu; }
    public void setIdProfilu(Long idProfilu) { this.idProfilu = idProfilu; }

    public String getNazwaProfil() { return nazwaProfil; }
    public void setNazwaProfil(String nazwaProfil) { this.nazwaProfil = nazwaProfil; }

    public String getSteamApiKey() { return steamApiKey; }
    public void setSteamApiKey(String steamApiKey) { this.steamApiKey = steamApiKey; }

    public String getSteamUserLogin() { return steamUserLogin; }
    public void setSteamUserLogin(String steamUserLogin) { this.steamUserLogin = steamUserLogin; }

    public Date getDataRejestracji() { return dataRejestracji; }
    public void setDataRejestracji(Date dataRejestracji) { this.dataRejestracji = dataRejestracji; }

    public Integer getCalkowitaLiczbaGodzin() { return calkowitaLiczbaGodzin; }
    public void setCalkowitaLiczbaGodzin(Integer calkowitaLiczbaGodzin) { this.calkowitaLiczbaGodzin = calkowitaLiczbaGodzin; }

    @Override
    public String toString() {
        return nazwaProfil;
    }
}
