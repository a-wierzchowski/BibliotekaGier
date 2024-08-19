package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gry")
public class Gry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_gry;
    private String tytul_gry;
    private Long steamappid;

    @OneToMany(mappedBy = "id_gry", cascade = CascadeType.ALL, orphanRemoval = true)  // SascadeType.All - odpowiada za dodawanie/usuwanie wszędzie w powiązanych
    private Set<Posiadane> posiadane = new HashSet<>();

    public void setId_gry(Long id_gry) {
        this.id_gry = id_gry;
    }

    public void setTytul_gry(String tytul_gry) {
        this.tytul_gry = tytul_gry;
    }

    public void setSteamappid(Long steamappid) {
        this.steamappid = steamappid;
    }

    public void setPosiadane(Set<Posiadane> posiadane) {
        this.posiadane = posiadane;
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

    public Set<Posiadane> getPosiadane() {
        return posiadane;
    }
}
