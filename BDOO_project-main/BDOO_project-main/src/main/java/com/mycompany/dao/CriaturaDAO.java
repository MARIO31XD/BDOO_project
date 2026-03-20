package com.mycompany.dao;

import com.mycompany.model.Criatura;
import com.mycompany.model.CostMana;
import com.mycompany.model.Raresa;
import com.mycompany.util.BDOOUtil;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

        continuar = false;

        // cost mana
        System.out.println("Cost mana:");
        CostMana cost = null;

        do {
            try {
                System.out.println("Blanc: (introdueix numero sencer)");
                int blanc = Integer.parseInt(s.nextLine());

                System.out.println("Blau:");
                int blau = Integer.parseInt(s.nextLine());

                System.out.println("Negre:");
                int negre = Integer.parseInt(s.nextLine());

                System.out.println("Vermell:");
                int vermell = Integer.parseInt(s.nextLine());

                System.out.println("Verd:");
                int verd = Integer.parseInt(s.nextLine());

                System.out.println("Incolor:");
                int incolor = Integer.parseInt(s.nextLine());

                cost = new CostMana(blanc, blau, negre, vermell, verd, incolor);
                continuar = true;

            } catch (NumberFormatException e) {
                System.out.println("Has introduit un caràcter que no és un numero, torna a intentar.");
            }
        } while (!continuar);

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

    public Criatura actualitzarCriatura(Criatura criatura) {

        CartaDAO cartaDAO = new CartaDAO();
        Scanner s = new Scanner(System.in);
        boolean continuar = false;
        Raresa raresa = null;
        CostMana cost = null;

        System.out.println("Nom: [" + criatura.getNom() + "]");
        String nom = s.nextLine();

        System.out.println("Descripció: [" + criatura.getDescripcio() + "]");
        String descr = s.nextLine();

        System.out.println("Edició: [" + criatura.getEdicio() + "]");
        String edicio = s.nextLine();

        // raresa
        do {
            System.out.println("Raresa: [" + criatura.getRaresa() + "]");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");
            switch (s.nextLine()) {
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

        continuar = false;

        // cost mana
        System.out.println("Cost mana:");
        do {
            try {
                System.out.println("Blanc:");
                int blanc = Integer.parseInt(s.nextLine());
                System.out.println("Blau:");
                int blau = Integer.parseInt(s.nextLine());
                System.out.println("Negre:");
                int negre = Integer.parseInt(s.nextLine());
                System.out.println("Vermell:");
                int vermell = Integer.parseInt(s.nextLine());
                System.out.println("Verd:");
                int verd = Integer.parseInt(s.nextLine());
                System.out.println("Incolor:");
                int incolor = Integer.parseInt(s.nextLine());
                cost = new CostMana(blanc, blau, negre, vermell, verd, incolor);
                continuar = true;
            } catch (NumberFormatException e) {
                System.out.println("Has introduit un caràcter que no és un numero, torna a intentar.");
            }
        } while (!continuar);

        System.out.println("Força: [" + criatura.getForca() + "]");
        int forca = Integer.parseInt(s.nextLine());

        System.out.println("Resistència: [" + criatura.getResistencia() + "]");
        int resistencia = Integer.parseInt(s.nextLine());

        System.out.println("Tipus de criatura: [" + criatura.getTipusCriatura() + "]");
        String tipusCriatura = s.nextLine();

        System.out.println("Vola? [S/N] [" + criatura.isVola() + "]");
        boolean vola = s.nextLine().trim().equalsIgnoreCase("s");

        criatura.setNom(nom);
        criatura.setDescripcio(descr);
        criatura.setEdicio(edicio);
        criatura.setRaresa(raresa);
        criatura.setCost(cost);
        criatura.setForca(forca);
        criatura.setResistencia(resistencia);
        criatura.setTipusCriatura(tipusCriatura);
        criatura.setVola(vola);

        cartaDAO.actualitzarCarta(criatura);

        return criatura;
    }

    public Criatura obtenirCriaturaPerID(int id) {
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            TypedQuery<Criatura> query = em.createQuery( "SELECT c FROM Criatura c WHERE c.id = :id", Criatura.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No s'ha trobat cap criatura amb ID " + id);
            return null;
        } finally {
            em.close();
        }
    }

}
