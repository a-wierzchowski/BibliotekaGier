package com.example.bibliotekagier.database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "platformy")
public class Platformy {
    @Id
    @GeneratedValue
    private Long id_platformy;
    private String nazwa_platformy;

    @OneToMany(mappedBy = "id_platformy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Posiadane> posiadane = new HashSet<>(); // Set zapewnia unikalność (list nie)

    public void setId_platformy(Long id_platformy) {
        this.id_platformy = id_platformy;
    }

    public void setNazwa_platformy(String nazwa_platformy) {
        this.nazwa_platformy = nazwa_platformy;
    }

    public void setPosiadane(Set<Posiadane> posiadane) {
        this.posiadane = posiadane;
    }

    public Long getId_platformy() {
        return id_platformy;
    }

    public String getNazwa_platformy() {
        return nazwa_platformy;
    }

    public Set<Posiadane> getPosiadane() {
        return posiadane;
    }
}
