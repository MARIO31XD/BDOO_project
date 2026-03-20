package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CartaDAO {

    // CREATE
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
            System.out.println("ERROR: No s'ha pogut crear la carta.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /// UPDATE
    
    public void actualitzarCarta(Carta c) {

        EntityManager em = BDOOUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            //merge perque la carta ve de fora. el merge ja sap on guardarla
            em.merge(c);
            em.getTransaction().commit();
            System.out.println("Carta actualitzada.");
        } catch (Exception e) {
            System.out.println("ERROR: Carta no actualitzada.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // READ
    //obtenir per id
    public Carta obtenirCartaPerID(int id) {

        EntityManager em = BDOOUtil.getEntityManager();

        TypedQuery<Carta> query = em.createQuery("SELECT e FROM Carta e WHERE e.id = :id", Carta.class);

        query.setParameter("id", id);
        
        Carta result = query.getSingleResult();
        
        em.close();
        
        return result;
        
        //para imprimir la carta hacer: Carta c = cDAO.obtenirCartaPerID(id); System.out.print(c);
    }


    //obtenir totes les cartes
    public void obtenirTotesLesCartes() {

        EntityManager em = BDOOUtil.getEntityManager();

        TypedQuery<Carta> query = em.createQuery("SELECT c FROM Carta c", Carta.class);
        List<Carta> resultats = query.getResultList();
        for (Carta c : resultats) {
            System.out.println(c);
        }
        
        em.close();

    }
    
    // DELETE
    public void eliminarCarta(Carta c) {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            
            //antes de borrar hay que acoplar la carta al entitymanager creado aqui
            Carta cartaMerged = em.merge(c);
            em.remove(cartaMerged);
            
            em.getTransaction().commit();
            System.out.println("Carta eiminada.");
        } catch (Exception e) {
            System.out.println("ERROR: carta no trobada.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            
        } finally {
            em.close();
        }
    }

}
