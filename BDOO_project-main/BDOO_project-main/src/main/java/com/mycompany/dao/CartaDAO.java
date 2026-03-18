package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CartaDAO {

    // Nota: El mètode crearCarta ara rep un objecte Carta (que serà una Terra o Criatura)
    public void guardarCarta(Carta carta) {
        EntityManager em = BDOOUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(carta); // Això funciona per a qualsevol filla de Carta
            tx.commit();
            System.out.println("Carta guardada correctament: " + carta.getNom());
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("Error al guardar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void llistarCartes() {
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            TypedQuery<Carta> query = em.createQuery("SELECT c FROM Carta c", Carta.class);
            List<Carta> resultats = query.getResultList();
            for (Carta ca : resultats) {
                System.out.println("Nom: " + ca.getNom() + " | Edició: " + ca.getEdicio());
            }
        } finally {
            em.close();
        }
    }
}