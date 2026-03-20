package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.CostMana;
import com.mycompany.model.Raresa;
import com.mycompany.model.Terra;
import com.mycompany.model.Terra.ColorProduccio;
import com.mycompany.util.BDOOUtil;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class TerraDAO {

    public void crearTerra() {

        CartaDAO cartaDAO = new CartaDAO();
        Scanner s = new Scanner(System.in);
        boolean continuar = false;
        Raresa raresa = null;
        ColorProduccio colorProduccio = null;
        CostMana cost = null;

        System.out.println("Introdueix un nom:");
        String nom = s.nextLine();

        System.out.println("Introdueix una descripció:");
        String descripcio = s.nextLine();

        System.out.println("Introdueix la edició:");
        String edicio = s.nextLine();

        System.out.println("Introdueix el tipus:");
        String tipus = s.nextLine();

        // raresa
        do {
            System.out.println("Raresa:");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");
            System.out.print("Selecciona: ");
            switch (s.nextLine()) {
                case "1":
                    raresa = Raresa.COMUNA;
                    continuar = true;
                    break;
                case "2":
                    raresa = Raresa.INFREQUENT;
                    continuar = true;
                    break;
                case "3":
                    raresa = Raresa.RARA;
                    continuar = true;
                    break;
                case "4":
                    raresa = Raresa.MITICA;
                    continuar = true;
                    break;
                default:
                    System.out.println("Raresa incorrecta, torna a provar.");
                    break;
            }
        } while (!continuar);

        continuar = false;


        // color produccio
        do {
            System.out.println("Color de producció de mana:");
            System.out.println("[1] Vermell");
            System.out.println("[2] Blau");
            System.out.println("[3] Verd");
            System.out.print("Selecciona: ");
            switch (s.nextLine()) {
                case "1":
                    colorProduccio = ColorProduccio.VERMELL;
                    continuar = true;
                    break;
                case "2":
                    colorProduccio = ColorProduccio.BLAU;
                    continuar = true;
                    break;
                case "3":
                    colorProduccio = ColorProduccio.VERD;
                    continuar = true;
                    break;
                default:
                    System.out.println("Color incorrecte, torna a provar.");
                    break;
            }
        } while (!continuar);

        continuar = false;

        // es basica
        boolean esBasica = false;
        do {
            System.out.println("És bàsica? [SI / NO]");
            String r = s.nextLine().trim().toLowerCase();
            if (r.equals("si")) {
                esBasica = true;
                continuar = true;
            } else if (r.equals("no")) {
                esBasica = false;
                continuar = true;
            } else {
                System.out.println("Resposta incorrecta, torna a escriure SI o NO.");
            }
        } while (!continuar);
        
        if (!esBasica) {

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
                System.out.println("Has d'introduir un número sencer.");
            }
        } while (!continuar);

        continuar = false;
        
        } else {
            cost = null;
        }

        Terra terra = new Terra(nom, descripcio, edicio, tipus, raresa, cost, colorProduccio, esBasica);
        cartaDAO.crearCarta(terra);
    }

    public Carta actualitzarTerra(Terra terra) {

        CartaDAO cartaDAO = new CartaDAO();
        Scanner s = new Scanner(System.in);
        boolean continuar = false;
        Raresa raresa = null;
        ColorProduccio colorProduccio = null;
        CostMana cost = null;

        System.out.println("Nom: [" + terra.getNom() + "]");
        String nom = s.nextLine();

        System.out.println("Descripció: [" + terra.getDescripcio() + "]");
        String descripcio = s.nextLine();

        System.out.println("Edició: [" + terra.getEdicio() + "]");
        String edicio = s.nextLine();

        System.out.println("Tipus: [" + terra.getTipus() + "]");
        String tipus = s.nextLine();

        // raresa
        do {
            System.out.println("Raresa: [" + terra.getRaresa() + "]");
            System.out.println("[1] Comuna");
            System.out.println("[2] Rara");
            System.out.println("[3] Mítica");
            switch (s.nextLine()) {
                case "1":
                    raresa = Raresa.COMUNA;
                    continuar = true;
                    break;
                case "2":
                    raresa = Raresa.INFREQUENT;
                    continuar = true;
                    break;
                case "3":
                    raresa = Raresa.RARA;
                    continuar = true;
                    break;
                case "4":
                    raresa = Raresa.MITICA;
                    continuar = true;
                    break;
                default:
                    System.out.println("Raresa incorrecta.");
                    break;
            }
        } while (!continuar);

        continuar = false;
        

        // es basica
        boolean esBasica = false;
        do {
            
            System.out.println("És bàsica? [SI / NO] [" + terra.esBasica() + "]");
            String r = s.nextLine().trim().toLowerCase();
            
            if (r.equals("si")) {
                esBasica = true;
                continuar = true;
            } else if (r.equals("no")) {
                esBasica = false;
                continuar = true;
            } else {
                System.out.println("Resposta incorrecta, torna a escriure SI o NO.");
            }
        } while (!continuar);

        if (!esBasica) {

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
                System.out.println("Has d'introduir un número sencer.");
            }
        } while (!continuar);

        continuar = false;
        
        } else {
            cost = null;
        }

        // color produccio
        do {
            System.out.println("Color de producció: [" + terra.getColorProduccio() + "]");
            System.out.println("[1] Vermell");
            System.out.println("[2] Blau");
            System.out.println("[3] Verd");
            switch (s.nextLine()) {
                case "1":
                    colorProduccio = ColorProduccio.VERMELL;
                    continuar = true;
                    break;
                case "2":
                    colorProduccio = ColorProduccio.BLAU;
                    continuar = true;
                    break;
                case "3":
                    colorProduccio = ColorProduccio.VERD;
                    continuar = true;
                    break;
                default:
                    System.out.println("Color incorrecte.");
                    break;
            }
        } while (!continuar);

        continuar = false;

        terra.setNom(nom);
        terra.setDescripcio(descripcio);
        terra.setEdicio(edicio);
        terra.setTipus(tipus);
        terra.setRaresa(raresa);
        terra.setCost(cost);
        terra.setColorProduccio(colorProduccio);
        terra.setEsBasica(esBasica);

        cartaDAO.actualitzarCarta(terra);
        return terra;
    }

    public Terra obtenirTerraPerID(int id) {
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            TypedQuery<Terra> query = em.createQuery("SELECT t FROM Terra t WHERE t.id = :id", Terra.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No s'ha trobat cap terra amb ID " + id);
            return null;
        } finally {
            em.close();
        }
    }
}
