package com.example.bibliotekagier.database;

import com.lukaspradel.steamapi.data.json.ownedgames.Game;

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


}
