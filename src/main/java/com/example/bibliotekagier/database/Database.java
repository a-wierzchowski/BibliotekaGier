package com.example.bibliotekagier.database;

import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import javafx.geometry.Pos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import javax.persistence.*;


public class Database {
    private static EntityManagerFactory FACTORY;

    public Database() {
            FACTORY = Persistence.createEntityManagerFactory("MyUnit");
    }

    /*
       public List<Profile> getProfile() {
           TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p", Profile.class);
           return query.getResultList();
       }
         */

    public List<Profil> getProfile() {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Profil p ORDER BY p.id_profilu");
        return query.getResultList();
    }

    public List<Platformy> getPlatformy() {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Platformy p ORDER BY p.id_platformy ");
        return query.getResultList();
    }
    public List<Gry> getGry() {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Gry g ORDER BY g.tytul_gry");
        return query.getResultList();
    }
    public List<OcenyGier> getOcenyGier(Long profil) {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT o FROM OcenyGier o WHERE o.id_profilu.id_profilu = :profil ORDER BY o.id_oceny");
        query.setParameter("profil", profil);
        return query.getResultList();
    }
    public List<Posiadane> getPosiadane(Long profil) {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Posiadane p WHERE p.id_profilu.id_profilu = :profil ORDER BY p.id_gry.tytul_gry");
        query.setParameter("profil", profil);
        return query.getResultList();
    }
    public List<Posiadane> getPosiadane(Long profil, String title) {
        if (title.isEmpty()){
            return getPosiadane(profil);
        }
        title = title.trim(); // usuwa spacje na początku i koncu
        title = title.replaceAll("\\s+", " "); // usuwa wielokrotne spacje i zostawia jedną
        title = "%" + title + "%";

        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Posiadane p WHERE p.id_profilu.id_profilu = :profil AND p.id_gry.tytul_gry like :title");
        query.setParameter("profil", profil);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public double getAvgOcenyGier(Long game) {
        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT AVG(ocena_uzytkownika) FROM OcenyGier o WHERE o.id_gry.id_gry = :gra");
        query.setParameter("gra", game);

        return (double) query.getSingleResult();
    }

    public void deletePosiadane(Long profil){
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("DELETE FROM Posiadane p WHERE p.id_profilu.id_profilu = :profil");
        query.setParameter("profil", profil);
        query.executeUpdate();

        transaction.commit();
    }

    public void deleteOcenyGier(Long profil){
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("DELETE FROM OcenyGier o WHERE o.id_profilu.id_profilu = :profil");
        query.setParameter("profil", profil);
        query.executeUpdate();

        transaction.commit();
    }

    public List<Gry> findTitleGame(String title) {

        title = title.trim(); // usuwa spacje na początku i koncu
        title = title.replaceAll("\\s+", " "); // usuwa wielokrotne spacje i zostawia jedną
        title = "%" + title + "%";

        EntityManager em = FACTORY.createEntityManager();
        Query query = em.createQuery("SELECT g FROM Gry g WHERE g.tytul_gry like :title");
        query.setParameter("title", title);
        return query.getResultList();
    }

    public void updateProfile(Long index,String name, String apiKey, String login){
        EntityManager entitymanager = FACTORY.createEntityManager();
        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();
        Profil profil = entitymanager.find(Profil.class, (long)index);
        if (!name.isEmpty()){
            profil.setNazwa_profil(name);
        }
        if (!apiKey.isEmpty()){
            profil.setSteamapikey(apiKey);
        }
        if (!login.isEmpty()){
            profil.setSteamuserlogin(login);
        }
        transaction.commit();
        entitymanager.close();
    }
    public void deletePosiadane(String title, Long indexProfil){
        EntityManager entitymanager = FACTORY.createEntityManager();
        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();

        Query query = entitymanager.createQuery("SELECT g FROM Gry g WHERE g.id_gry.tytul_gry = :title");
        query.setParameter("title", title);
        Gry gra = (Gry) query.getSingleResult();

        query = entitymanager.createQuery("SELECT p FROM Profil p WHERE p.id_profilu.id_profilu = :indexProfil");
        query.setParameter("indexProfil", indexProfil);
        Profil profil = (Profil) query.getSingleResult();

        Long id_gry = gra.getId_gry();

        query = entitymanager.createQuery("SELECT p FROM Posiadane p WHERE p.id_gry = :id_gry AND p.id_profilu = :id_profilu");
        query.setParameter("id_gry", gra);
        query.setParameter("id_profilu", profil);
        List<Posiadane> posiadaneList = query.getResultList();

        for (Posiadane p : posiadaneList){
            query = entitymanager.createQuery("DELETE FROM Posiadane p WHERE p.id_zakupu = :row");
            query.setParameter("row", p.getId_zakupu());
            query.executeUpdate();
        }

        transaction.commit();
        entitymanager.close();
    }
    public void updateOcenyGier(String title, int ocena, String komentarz, Long indexProfil){
        EntityManager entitymanager = FACTORY.createEntityManager();
        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();

        Query query = entitymanager.createQuery("SELECT g FROM Gry g WHERE g.tytul_gry = :title");
        query.setParameter("title", title);
        Gry gra = (Gry) query.getSingleResult();

        query = entitymanager.createQuery("SELECT p FROM Profil p WHERE p.id_profilu = :indexProfil");
        query.setParameter("indexProfil", indexProfil);
        Profil profil = (Profil) query.getSingleResult();

        Long id_gry = gra.getId_gry();

        query = entitymanager.createQuery("SELECT o FROM OcenyGier o WHERE o.id_gry = :id_gry AND o.id_profilu = :id_profilu");
        query.setParameter("id_gry", gra);
        query.setParameter("id_profilu", profil);
        OcenyGier ocenyGier = (OcenyGier) query.getSingleResult();

        ocenyGier.setId_gry(gra);
        ocenyGier.setOcena_uzytkownika(ocena);
        if (!komentarz.isEmpty()){
            ocenyGier.setKomentarz(komentarz);
        }
        transaction.commit();
        entitymanager.close();
    }

    public void addGame(String title, Long steamAppID){

        title = title.trim(); // usuwa spacje na początku i koncu
        title = title.replaceAll("\\s+", " "); // usuwa wielokrotne spacje i zostawia jedną

        EntityManager entitymanager = FACTORY.createEntityManager();

        Query query = entitymanager.createQuery("SELECT COUNT(g) FROM Gry g WHERE g.tytul_gry = :title");
        query.setParameter("title", title);
        Long exist = (Long) query.getSingleResult();
        if (exist > 0)
            throw new IllegalArgumentException("Gra o tytule '" + title + "' już istnieje w bazie");

        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();
        Gry gry = new Gry();
        if (!title.isEmpty()){
            gry.setTytul_gry(title);
        }
        if (steamAppID != null){
            gry.setSteamappid(steamAppID);
        }
        entitymanager.merge(gry);
        transaction.commit();
        entitymanager.close();
    }

    public void addGameToLibrary(String title, String platforma, String status, Long indexProfil){


        EntityManager entitymanager = FACTORY.createEntityManager();

        Query query = entitymanager.createQuery("SELECT g FROM Gry g WHERE g.tytul_gry = :title");
        query.setParameter("title", title);
        Gry gra = (Gry) query.getSingleResult();

        query = entitymanager.createQuery("SELECT p FROM Platformy p WHERE p.nazwa_platformy = :palatforma");
        query.setParameter("palatforma", platforma);
        Platformy platformy = (Platformy) query.getSingleResult();

        query = entitymanager.createQuery("SELECT p FROM Profil p WHERE p.id_profilu = :indexProfil");
        query.setParameter("indexProfil", indexProfil);
        Profil profil = (Profil) query.getSingleResult();

        Long id_gry = gra.getId_gry();
        Long id_platformy = platformy.getId_platformy();

        query = entitymanager.createQuery("SELECT COUNT(p) FROM Posiadane p WHERE p.id_gry = :id_gry AND p.id_platformy = :id_platformy");
        query.setParameter("id_gry", gra);
        query.setParameter("id_platformy", platformy);
        Long exist = (Long) query.getSingleResult();
        if (exist > 0)
            throw new IllegalArgumentException("Gra o tytule '" + title + "' na platformie '" + platforma + "' już jest w Bibliotece");

        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();

        Posiadane posiadane = new Posiadane();
        posiadane.setId_gry(gra);
        posiadane.setId_platformy(platformy);
        posiadane.setStatus(status);
        posiadane.setId_profilu(profil);
        entitymanager.merge(posiadane);
        transaction.commit();
        entitymanager.close();
    }


    public void addOcenyGier(String title, int ocena, String komentarz, Long indexProfil){

        EntityManager entitymanager = FACTORY.createEntityManager();

        Query query = entitymanager.createQuery("SELECT g FROM Gry g WHERE g.tytul_gry = :title");
        query.setParameter("title", title);
        Gry gra = (Gry) query.getSingleResult();

        query = entitymanager.createQuery("SELECT p FROM Profil p WHERE p.id_profilu = :indexProfil");
        query.setParameter("indexProfil", indexProfil);
        Profil profil = (Profil) query.getSingleResult();

        Long id_gry = gra.getId_gry();

        query = entitymanager.createQuery("SELECT COUNT(o) FROM OcenyGier o WHERE o.id_gry = :id_gry AND o.id_profilu = :id_profilu");
        query.setParameter("id_gry", gra);
        query.setParameter("id_profilu", profil);
        Long exist = (Long) query.getSingleResult();
        if (exist > 0)
            throw new IllegalArgumentException("Gra o tytule '" + title +"' została wcześniej oceniona");

        EntityTransaction transaction = entitymanager.getTransaction();
        transaction.begin();

        OcenyGier ocenyGier = new OcenyGier();
        ocenyGier.setId_gry(gra);
        ocenyGier.setId_profilu(profil);
        ocenyGier.setOcena_uzytkownika(ocena);
        ocenyGier.setKomentarz(komentarz);
        entitymanager.merge(ocenyGier);
        transaction.commit();
        entitymanager.close();
    }


}
