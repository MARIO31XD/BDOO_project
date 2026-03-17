package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.CostMana;
import com.mycompany.model.Encanteri;
import com.mycompany.model.Raresa;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class EncanteriDAO {
    
    public void crearEncanteri(Encanteri enc) {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            //comença la transaccio
            em.getTransaction().begin();
            //guardar
            em.persist(enc);
            //guardar canvis al fitxer
            em.getTransaction().commit();
            
            System.out.println("Encanteri creat.");
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        
    }
    
}
