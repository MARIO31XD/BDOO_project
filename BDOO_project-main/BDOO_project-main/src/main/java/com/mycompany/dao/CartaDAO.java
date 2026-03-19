package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.Encanteri;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CartaDAO {

    //crear
    public void crearCarta(Carta c) {

        EntityManager em = BDOOUtil.getEntityManager();

        try {

            //comença la transaccio
            em.getTransaction().begin();
            //guardar
            em.persist(c);
            //guardar canvis al fitxer
            em.getTransaction().commit();

            System.out.println("Carta creada.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //obtenir per id
    public Carta obtenirCartaPerID(int id) {

        EntityManager em = BDOOUtil.getEntityManager();

        TypedQuery<Carta> query = em.createQuery("SELECT e FROM Carta e WHERE e.id = :id", Carta.class);

        query.setParameter("id", id);

        
        return query.getSingleResult();
    }

    //obtenir totes les cartes
    public void obtenirTotesLesCartes() {

        EntityManager em = BDOOUtil.getEntityManager();

        TypedQuery<Carta> query = em.createQuery("SELECT c FROM Carta c", Carta.class);
        List<Carta> resultats = query.getResultList();
        for (Carta c : resultats) {
            System.out.println(c);
        }
        
    }

}
