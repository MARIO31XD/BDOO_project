package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.Criatura;
import com.mycompany.model.Jugador;
import com.mycompany.model.Mazo;
import com.mycompany.util.BDOOUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QueryDAO {

    public void assignarMazoAJugador() {
        Scanner s = new Scanner(System.in);

        System.out.print("Introdueix ID del jugador: ");
        int idJugador = llegirInt(s);
        System.out.print("Introdueix ID del mazo: ");
        int idMazo = llegirInt(s);

        EntityManager em = BDOOUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Jugador jugador = em.find(Jugador.class, (long) idJugador);
            Mazo mazo = em.find(Mazo.class, (long) idMazo);

            if (jugador == null) {
                System.out.println("Jugador no trobat.");
                em.getTransaction().rollback();
                return;
            }
            if (mazo == null) {
                System.out.println("Mazo no trobat.");
                em.getTransaction().rollback();
                return;
            }

            jugador.afegirMazo(mazo);

            em.getTransaction().commit();
            System.out.println("Mazo assignat al jugador correctament.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void treureMazoDeJugador() {
        Scanner s = new Scanner(System.in);

        System.out.print("Introdueix ID del jugador: ");
        int idJugador = llegirInt(s);
        System.out.print("Introdueix ID del mazo a treure: ");
        int idMazo = llegirInt(s);

        EntityManager em = BDOOUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Jugador jugador = em.find(Jugador.class, (long) idJugador);
            Mazo mazo = em.find(Mazo.class, (long) idMazo);

            if (jugador == null) {
                System.out.println("Jugador no trobat.");
                em.getTransaction().rollback();
                return;
            }
            if (mazo == null) {
                System.out.println("Mazo no trobat.");
                em.getTransaction().rollback();
                return;
            }

            jugador.getMazos().removeIf(m -> m.getId().equals(mazo.getId()));

            em.getTransaction().commit();
            System.out.println("Mazo tret del jugador correctament.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // CRIATURAS QUE VOLEN AMB COST DE MANA D'UN COLOR I VALOR
    public void criaturesQueVolenAmbCostMana() {
        Scanner s = new Scanner(System.in);

        System.out.println("Selecciona color de mana:");
        System.out.println("[1] Blanc  [2] Blau  [3] Negre  [4] Vermell  [5] Verd  [6] Incolor");
        int opcioColor = llegirInt(s);

        System.out.print("Valor mínim del cost: ");
        int valor = llegirInt(s);

        String colorCamp;
        switch (opcioColor) {
            case 1:
                colorCamp = "cost.blanc";
                break;
            case 2:
                colorCamp = "cost.blau";
                break;
            case 3:
                colorCamp = "cost.negre";
                break;
            case 4:
                colorCamp = "cost.vermell";
                break;
            case 5:
                colorCamp = "cost.verd";
                break;
            case 6:
                colorCamp = "cost.incolor";
                break;
            default:
                System.out.println("Color no vàlid.");
                return;
        }

        EntityManager em = BDOOUtil.getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT c.nom, c.forca, c.resistencia, " + colorCamp
                    + " FROM Criatura c WHERE c.vola = true AND " + colorCamp + " >= :valor",
                    Object[].class);
            query.setParameter("valor", valor);
            List<Object[]> resultats = query.getResultList();

            if (resultats.isEmpty()) {
                System.out.println("No s'han trobat criaturas que compleixin els criteris.");
            } else {
                System.out.println("Criaturas que volen amb cost >= " + valor + ":");
                for (Object[] fila : resultats) {
                    System.out.println("  Nom: " + fila[0]
                            + " | Força: " + fila[1]
                            + " | Resistència: " + fila[2]
                            + " | Cost: " + fila[3]);
                }
            }
        } finally {
            em.close();
        }
    }
public void mitjaForcaCriaturesDelMazo() {
    Scanner s = new Scanner(System.in);

    System.out.print("Introdueix ID del jugador: ");
    int idJugador = llegirInt(s);
    System.out.print("Introdueix ID del mazo: ");
    int idMazo = llegirInt(s);

    EntityManager em = BDOOUtil.getEntityManager();
    try {
        Jugador jugador = em.find(Jugador.class, (long) idJugador);
        if (jugador == null) { System.out.println("Jugador no trobat."); return; }

        Mazo mazo = em.find(Mazo.class, (long) idMazo);
        if (mazo == null) { System.out.println("Mazo no trobat."); return; }

        boolean pertany = jugador.getMazos().stream()
            .anyMatch(m -> m.getId().equals(mazo.getId()));
        if (!pertany) { System.out.println("Aquest mazo no pertany al jugador indicat."); return; }

        // obtenir els IDs de les criaturas del mazo
        List<Long> ids = new ArrayList<>();
        for (Carta c : mazo.getCartes()) {
            ids.add(c.getId());
        }

        if (ids.isEmpty()) { System.out.println("El mazo no té cartes."); return; }

        // AVG sobre criaturas filtrant per ID
        TypedQuery<Double> query = em.createQuery(
            "SELECT AVG(c.forca) FROM Criatura c WHERE c.id IN :ids", Double.class);
        query.setParameter("ids", ids);

        Double mitjana = query.getSingleResult();
        if (mitjana == null) {
            System.out.println("El mazo no té criaturas.");
        } else {
            System.out.printf("Mitja de força de les criaturas del mazo: %.2f%n", mitjana);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        em.close();
    }
}

    // ENCANTERIS SENSE MANA BLAU NI BLANC I AMB INCOLOR > 3
    public void encanterisIncolorsSenseBlauNiBlanc() {
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT e.nom, e.descripcio, e.cost.incolor FROM Encanteri e "
                    + "WHERE e.cost.blau = 0 AND e.cost.blanc = 0 AND e.cost.incolor > 3",
                    Object[].class);

            List<Object[]> resultats = query.getResultList();

            if (resultats.isEmpty()) {
                System.out.println("No s'han trobat encanteris amb aquests criteris.");
            } else {
                System.out.println("Encanteris sense mana blau ni blanc i amb incolor > 3:");
                for (Object[] fila : resultats) {
                    System.out.println("  Nom: " + fila[0]
                            + " | Descripció: " + fila[1]
                            + " | Cost incolor: " + fila[2]);
                }
            }
        } finally {
            em.close();
        }
    }

    private int llegirInt(Scanner s) {
        while (true) {
            try {
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Has d'introduir un número sencer.");
            }
        }
    }

}
