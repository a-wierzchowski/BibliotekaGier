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


}
