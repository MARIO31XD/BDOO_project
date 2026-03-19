
package com.mycompany.dao;

import com.mycompany.model.Criatura;
import com.mycompany.model.CostMana;
import com.mycompany.model.Raresa;
import com.mycompany.util.BDOOUtil;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class CriaturaDAO {
  
    public void crearCriatura() {
        
        EntityManager em = BDOOUtil.getEntityManager();
        Scanner s = new Scanner(System.in);
        boolean continuar = false;
        Raresa raresa = null;


        System.out.println("Introdueix nom:");
        String nom = s.nextLine();

        System.out.println("Descripció:");
        String descr = s.nextLine();
        
        System.out.println("Edició:");
        String edicio = s.nextLine();

        // Raresa 
        do {
            System.out.println("Selecciona raresa: [1] Comuna, [2] Rara, [3] Mítica");
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
        } while (!continuar);

        // Cost de Mana
        System.out.println("Cost mana (introdueix números):");
        System.out.print("Blanc: "); int blanc = Integer.parseInt(s.nextLine());
        System.out.print("Blau: "); int blau = Integer.parseInt(s.nextLine());
        System.out.print("Negre: "); int negre = Integer.parseInt(s.nextLine());
        System.out.print("Vermell: "); int vermell = Integer.parseInt(s.nextLine());
        System.out.print("Verd: "); int verd = Integer.parseInt(s.nextLine());
        System.out.print("Incolor: "); int incolor = Integer.parseInt(s.nextLine());

        CostMana cost = new CostMana(blanc, blau, negre, vermell, verd, incolor);

 
        System.out.println("Força:");
        int forca = Integer.parseInt(s.nextLine());

        System.out.println("Resistència:");
        int resistencia = Integer.parseInt(s.nextLine());

        System.out.println("Tipus de criatura (ex: Drac, Guerrer):");
        String tipusCriatura = s.nextLine();

        System.out.println("Vola? (S/N):");
        boolean vola = s.nextLine().equalsIgnoreCase("S");

    
        Criatura criatura = new Criatura(nom, descr, edicio, raresa, cost, forca, resistencia, tipusCriatura, vola);

        try {
            em.getTransaction().begin();
            em.persist(criatura);
            em.getTransaction().commit();
            System.out.println("Criatura creada correctament!");
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
