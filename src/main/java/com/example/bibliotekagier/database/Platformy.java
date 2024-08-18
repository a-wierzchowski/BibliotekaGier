package com.example.bibliotekagier.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "platformy")
public class Platformy {
    @Id
    @GeneratedValue
    private Long id_platformy;
    private String nazwa_platformy;

    public void setID_platformy(Long ID_platformy) {
        this.id_platformy = ID_platformy;
    }

    public void setNazwa_platformy(String nazwa_platformy) {
        this.nazwa_platformy = nazwa_platformy;
    }

    public Long getID_platformy() {
        return id_platformy;
    }

    public String getNazwa_platformy() {
        return nazwa_platformy;
    }
}
