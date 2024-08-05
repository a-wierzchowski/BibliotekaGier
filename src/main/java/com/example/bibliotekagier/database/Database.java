package com.example.bibliotekagier.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Database {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("MyUnit");

    /*
    private static EntityManager em = FACTORY.createEntityManager();

   public List<Profile> getProfile() {
       TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p", Profile.class);
       return query.getResultList();
   }
     */

}
