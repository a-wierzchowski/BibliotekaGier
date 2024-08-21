package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posiadane")
public class Posiadane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_zakupu;
    @ManyToOne
    @JoinColumn(name = "id_profilu")
    private Profil id_profilu;
    @ManyToOne
    @JoinColumn(name = "id_gry")
    private Gry id_gry;
    @ManyToOne
    @JoinColumn(name = "id_platformy")
    private Platformy id_platformy;

    private int cena_zakupu;
    private Date data_zakupu;
    private String status;
    private String klucz_aktywacji;

    public void setId_zakupu(Long id_zakupu) {
        this.id_zakupu = id_zakupu;
    }

    public void setId_profilu(Profil id_profilu) {
        this.id_profilu = id_profilu;
    }

    public void setId_gry(Gry id_gry) {
        this.id_gry = id_gry;
    }

    public void setId_platformy(Platformy id_platformy) {
        this.id_platformy = id_platformy;
    }

    public void setCena_zakupu(int cena_zakupu) {
        this.cena_zakupu = cena_zakupu;
    }

    public void setData_zakupu(Date data_zakupu) {
        this.data_zakupu = data_zakupu;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKlucz_aktywacji(String klucz_aktywacji) {
        this.klucz_aktywacji = klucz_aktywacji;
    }

    public Long getId_zakupu() {
        return id_zakupu;
    }

    public Profil getId_profilu() {
        return id_profilu;
    }

    public Gry getId_gry() {
        return id_gry;
    }

    public Platformy getId_platformy() {
        return id_platformy;
    }

    public int getCena_zakupu() {
        return cena_zakupu;
    }

    public Date getData_zakupu() {
        return data_zakupu;
    }

    public String getStatus() {
        return status;
    }

    public String getKlucz_aktywacji() {
        return klucz_aktywacji;
    }
}
