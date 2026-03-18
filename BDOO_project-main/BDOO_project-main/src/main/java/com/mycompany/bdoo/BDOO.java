package com.mycompany.bdoo;

import com.mycompany.util.BDOOUtil;
import com.mycompany.model.*;
import javax.persistence.EntityManager;
import java.util.Scanner;

public class BDOO {

    public static void main(String[] args) {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("--- Poly-Deck Engine: Menú d'Usuari ---");
            System.out.println("1. Crear una Criatura");
            System.out.println("2. Crear una Terra");
            System.out.print("Selecciona una opció: ");
            int opcio = Integer.parseInt(s.nextLine());

            System.out.print("Nom: ");
            String nom = s.nextLine();
            System.out.print("Descripció: ");
            String desc = s.nextLine();
            System.out.print("Edició: ");
            String edicio = s.nextLine();

            // Cost de manà (podries demanar-lo per teclat, aquí el posem buit per agilitzar)
            CostMana cost = new CostMana(0, 0, 0, 0, 0, 0);

            EntityManager em = BDOOUtil.getEntityManager();
            try {
                em.getTransaction().begin();

                if (opcio == 1) {
                    // Creació de Criatura interactiva
                    System.out.print("Força: ");
                    int forca = Integer.parseInt(s.nextLine());
                    System.out.print("Resistència: ");
                    int res = Integer.parseInt(s.nextLine());
                    System.out.print("Tipus de Criatura: ");
                    String tipus = s.nextLine();
                    System.out.print("Vola? (si/no): ");
                    boolean vola = s.nextLine().equalsIgnoreCase("si");

                    Criatura nuevaC = new Criatura(nom, desc, edicio, Raresa.RARA, cost, forca, res, tipus, vola);
                    em.persist(nuevaC);
                    System.out.println("✅ Criatura '" + nom + "' guardada.");

                } else if (opcio == 2) {
                    // Creació de Terra interactiva
                    System.out.print("És bàsica? (si/no): ");
                    boolean basica = s.nextLine().equalsIgnoreCase("si");
                    
                    Terra nuevaT = new Terra(nom, desc, edicio, Raresa.COMUNA, cost, "Terreny", Terra.ColorProduccio.BLAU, basica);
                    em.persist(nuevaT);
                    System.out.println("✅ Terra '" + nom + "' guardada.");
                }

                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) em.getTransaction().rollback();
                System.err.println("Error: " + e.getMessage());
            } finally {
                em.close();
                BDOOUtil.tancarConexio();
            }
        }
    }
}