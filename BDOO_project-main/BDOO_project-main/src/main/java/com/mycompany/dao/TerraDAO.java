package com.mycompany.dao;

import com.mycompany.model.Terra;
import com.mycompany.util.BDOOUtil;
import java.util.Scanner;
import javax.persistence.EntityManager;


public class TerraDAO {

    public void crearTerra(Terra terra) {

        EntityManager em = BDOOUtil.getEntityManager();
        
        Scanner sn = new Scanner(System.in);
        System.out.println("Introdueix un nom:");
        sn.nextLine();

    try {
         
        // begin transaction
         em.getTransaction().begin();
         //guardar persisteix al object terra
         em.persist(terra);
         
         // commitejar els canvis
         em.getTransaction().commit();

         System.out.println(" Terra creada correctament");



    }
    catch(Exception e ){
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback(); // rollback per si falla la transaction

        }
        e.printStackTrace();

    }
    finally {
        em.close();
    }



        

    }

}
