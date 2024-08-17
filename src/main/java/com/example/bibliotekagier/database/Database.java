package com.example.bibliotekagier.database;

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
}
