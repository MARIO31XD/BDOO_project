package com.mycompany.dao;

import com.mycompany.model.Terra;
import com.mycompany.util.BDOOUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TerraDAO {

    public void crearTerra(Terra terra) {
        EntityManager em = BDOOUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Començar la transacció
            tx.begin();
            
            // Persistir l'objecte terra
            em.persist(terra);
            
            // Confirmar els canvis
            tx.commit();

            System.out.println("Terra creada correctament: " + terra.getNom());

        } catch (Exception e) {
            // Corregit: isActive() és un mètode i rollback() va en minúscules
            if (tx != null && tx.isActive()) {
                tx.rollback(); 
                System.err.println("Error en la transacció, s'ha fet rollback.");
            }
            e.printStackTrace();
        } finally {
            // Tancar l'EntityManager sempre
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}