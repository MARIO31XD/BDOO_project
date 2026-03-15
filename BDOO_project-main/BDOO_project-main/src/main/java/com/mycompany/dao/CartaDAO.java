package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CartaDAO {

    //crear
    public void crearCarta(String nom, String descripcio, String edicio) {
        
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/cartes.odb");
            EntityManager em = BDOOUtil.getEntityManager();

        //try {
        try {
            //començar la transaccio
            em.getTransaction().begin();
            
            //crear la carta amb els valors que entren per el metode
            Carta c = new Carta(nom, descripcio, edicio);
            
            //guardar la carta
            em.persist(c);
            em.getTransaction().commit();
            
            System.out.println("Carta guardada.");

            //omstrar la carta test
            System.out.println("Mostrar carta:");

            TypedQuery<Carta> query = em.createQuery("SELECT c FROM Carta c WHERE c.nom = 'Cloud'", Carta.class);
            List<Carta> resultats = query.getResultList();

            for (Carta ca : resultats) {
                System.out.println(ca.getNom());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
