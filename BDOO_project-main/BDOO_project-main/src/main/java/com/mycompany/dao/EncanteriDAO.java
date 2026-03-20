package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.CostMana;
import com.mycompany.model.Encanteri;
import com.mycompany.model.Raresa;
import com.mycompany.util.BDOOUtil;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EncanteriDAO {

    public void crearEncanteri() {

        CartaDAO cartaDAO = new CartaDAO();

        boolean continuar = false;

        Raresa raresa = null;
        boolean esInstantani = false;

        Scanner s = new Scanner(System.in);

        //comença la transaccio
        System.out.println("Inrodueix nom:");
        String nom = s.nextLine();
        System.out.println("Descripció:");
        String descr = s.nextLine();
        System.out.println("Edició:");
        String edicio = s.nextLine();

        // raresa
        do {

            System.out.println("Raresa:");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");

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

        } while (!continuar);

        //reinicia continuar per reutilitrzarla
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

        // tipus
        System.out.println("Introdueix tipus:");
        String tipus = s.nextLine();

        continuar = false;
        
        // es instantania        
        do {

            System.out.println("Es instantani? [SI / NO]");
            String resposta = s.nextLine();
            // passem a minuscules
            String r = resposta.trim().toLowerCase();
            // comprobem
            if (r.equals("si")) {
                esInstantani = true;
                continuar = true;
            } else if (r.equals("no")) {
                esInstantani = false;
                continuar = true;
            } else {
                System.out.println("Resposta incorrecta, torna a escriure SI o NO.");
            }

        } while (!continuar);

        //creem objecte
        Encanteri enc = new Encanteri(nom, descr, edicio, raresa, cost, tipus, esInstantani);
        //creem objecte per a la bd
        cartaDAO.crearCarta(enc);

    }

    //actualitzar encanteri
    public Carta actualitzarEncanteri(Encanteri enc) {

        CartaDAO cartaDAO = new CartaDAO();

        Scanner s = new Scanner(System.in);

        boolean continuar = false;

        Raresa raresa = null;
        boolean esInstantani = false;

        System.out.println("Nom:  [ " + enc.getNom() + "]");
        String nom = s.nextLine();
        System.out.println("Descripció: [ " + enc.getDescripcio() + "]");
        String descr = s.nextLine();
        System.out.println("Edició: [ " + enc.getEdicio() + "]");
        String edicio = s.nextLine();

        // raresa
        do {

            System.out.println("Raresa: [" + enc.getRaresa() + " ]");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");

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

        } while (!continuar);

        //reinicia continuar per reutilitrzarla
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

        // tipus
        System.out.println("Introdueix tipus: [" + enc.getTipus() + " ]");
        String tipus = s.nextLine();

        // es instantania     
        continuar = false;

        do {

            System.out.println("Es instantani? [SI / NO] [" + enc.isEsInstantani() + " ]");
            String resposta = s.nextLine();
            // passem a minuscules
            String r = resposta.toLowerCase();
            // comprobem
            if (r.equals("si")) {
                esInstantani = true;
                continuar = true;
            } else if (r.equals("no")) {
                esInstantani = false;
                continuar = true;
            } else {
                System.out.println("Resposta incorrecta, torna a escriure SI o NO.");
            }

        } while (!continuar);

        enc.setNom(nom);
        enc.setDescripcio(descr);
        enc.setEdicio(edicio);
        enc.setRaresa(raresa);
        enc.setCost(cost);
        enc.setTipus(tipus);
        enc.setEsInstantani(esInstantani);

        cartaDAO.actualitzarCarta(enc);

        return enc;
    }

    public Encanteri obtenirEncanteriPerID(int id) {

        EntityManager em = BDOOUtil.getEntityManager();

        TypedQuery<Encanteri> query = em.createQuery("SELECT e FROM Encanteri e WHERE e.id = :id", Encanteri.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

}
