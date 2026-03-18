package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.CostMana;
import com.mycompany.model.Encanteri;
import com.mycompany.model.Raresa;
import com.mycompany.util.BDOOUtil;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EncanteriDAO {

    public void crearEncanteri() {

        EntityManager em = BDOOUtil.getEntityManager();

        boolean continuar = false;

        Scanner s = new Scanner(System.in);

        System.out.println("Inrodueix nom:");
        String nom = s.nextLine();
        //String nom, String descripcio, Raresa raresa, CostMana cost, String tipus, boolean esInstantani
        System.out.println("Descripció:");
        String descr = s.nextLine();

        // raresa
        do {

            System.out.println("Raresa:");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");

            Raresa raresa = null;

            System.out.println("Selecciona raresa: [1, 2, 3]");
            String r = s.nextLine();

            switch (r) {
                case "1":
                    raresa = Raresa.COMUNA;
                    continuar = true;
                    break;
                case "2":
                    raresa = Raresa.RARA;
                    continuar = true;
                    break;
                case "3":
                    raresa = Raresa.MITICA;
                    continuar = true;
                    break;
                default:
                    System.out.println("Raresa incorrecta, torna a provar.");
                    break;
            }

        } while (continuar == false);

        //reinicia continuar per reutilitrzarla
        continuar = false;

        // cost mana
        System.out.println("Cost mana:");

        System.out.println("Blanc: (introdueix numero sencer)");
        int blanc = s.nextInt();
        s.nextLine();

        System.out.println("Blau:");
        int blau = s.nextInt();
        s.nextLine();

        System.out.println("Negre:");
        int negre = s.nextInt();
        s.nextLine();

        System.out.println("Vermell:");
        int vermell = s.nextInt();
        s.nextLine();

        System.out.println("Verd:");
        int verd = s.nextInt();
        s.nextLine();

        System.out.println("Incolor:");
        int incolor = s.nextInt();
        s.nextLine();
        
        CostMana cost = new CostMana(blanc, blau, negre, vermell, verd, incolor);
        
        // tipus
        System.out.println("Introdueix tipus:");
        String tipus = s.nextLine();
        
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
