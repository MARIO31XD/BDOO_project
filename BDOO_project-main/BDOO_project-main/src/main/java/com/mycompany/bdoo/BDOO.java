package com.mycompany.bdoo;

import com.mycompany.dao.CartaDAO;
import com.mycompany.dao.EncanteriDAO;
import com.mycompany.dao.MazoDAO;
import com.mycompany.model.Carta;
import com.mycompany.model.Encanteri;
import com.mycompany.model.Mazo;
import com.mycompany.util.BDOOUtil;
import java.util.Scanner;

public class BDOO {

    public static void main(String[] args) {
        
        EncanteriDAO encDAO = new EncanteriDAO();
        CartaDAO cDAO = new CartaDAO();
        MazoDAO mazoDAO = new MazoDAO();
        Scanner s = new Scanner(System.in);

        System.out.println("-- POLY-DECK ENGINE: Magic The Gathering --");

        boolean executant = true;

        while (executant) {

            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("-- Cartes --");
            System.out.println("1. Crear carta");
            System.out.println("2. Veure totes les cartes");
            System.out.println("3. Veure carta per ID");
            System.out.println("4. Modificar carta");
            System.out.println("5. Eliminar carta");
            System.out.println("-- Mazos --");
            System.out.println("6. Crear mazo");
            System.out.println("7. Veure tots els mazos");
            System.out.println("8. Veure mazo per ID");
            System.out.println("9. Modificar mazo");
            System.out.println("10. Eliminar mazo");
            System.out.println("-- Jugadors --");
            System.out.println("--------------");
            System.out.println("0. Sortir");
            System.out.println("==========================");
            System.out.print("Selecciona una opció: ");

            String opcio = s.nextLine();

            switch (opcio) {

            case "1":
                System.out.println("-- Crear carta --");
                System.out.println("De quin tipus?");
                System.out.println("1. Encanteri");
                System.out.println("2. Criatura");
                System.out.println("3. Terra");
                System.out.print("Selecciona: ");
                
                String tipusCarta = s.nextLine();
                
                switch (tipusCarta) {
                    case "1":
                        encDAO.crearEncanteri();
                        break;
                    case "2":
                        // criaturaDAO.crearCriatura();
                        break;
                    case "3":
                        // terraDAO.crearTerra();
                        break;
                    default:
                        System.out.println("Opció no vàlida.");
                        break;
                }
                break;

                case "2":
                    System.out.println("-- Veure totes les cartes --");
                    cDAO.obtenirTotesLesCartes();
                    break;

                case "3":
                    System.out.println("-- Veure carta per ID --");
                    System.out.print("Introdueix ID: ");
                    
                    int idVeure = Integer.parseInt(s.nextLine());
                    
                    Carta cv = cDAO.obtenirCartaPerID(idVeure, Carta.class);
                    System.out.println(cv);
                    
                    break;

                case "4":
                    System.out.println("-- Modificar carta --");
                    System.out.print("Introdueix ID de la carta a modificar: ");
                    
                    int idm = Integer.parseInt(s.nextLine());
                    Encanteri cartaMod = encDAO.obtenirEncanteriPerID(idm);
                    
                    if (cartaMod != null) {
                        encDAO.actualitzarEncanteri(cartaMod);
                    } else {
                        System.out.println("Carta no trobada.");
                    }
                    break;

                case "5":
                    System.out.println("\n-- Eliminar carta --");
                    System.out.print("Introdueix ID de la carta a eliminar: ");
                    int idElim = Integer.parseInt(s.nextLine());
                    Carta cartaElim = cDAO.obtenirCartaPerID(idElim, Carta.class);
                    if (cartaElim != null) {
                        System.out.println(cartaElim);
                        cDAO.eliminarCarta(cartaElim);
                    } else {
                        System.out.println("Carta no trobada.");
                    }
                    break;

                case "6":
                    System.out.println("\n-- Crear mazo --");
                    mazoDAO.crearMazo();
                    break;

                case "7":
                    System.out.println("\n-- Tots els mazos --");
                    mazoDAO.obtenirTotsElsMazos();
                    break;

                case "8":
                    System.out.println("\n-- Veure mazo per ID --");
                    System.out.print("Introdueix ID: ");
                    int idMazoVeure = Integer.parseInt(s.nextLine());
                    Mazo mazoVeure = mazoDAO.obtenirMazoPerID(idMazoVeure);
                    System.out.println(mazoVeure);
                    break;

                case "9":
                    System.out.println("\n-- Modificar mazo --");
                    System.out.print("Introdueix ID del mazo a modificar: ");
                    int idMazoMod = Integer.parseInt(s.nextLine());
                    Mazo mazoMod = mazoDAO.obtenirMazoPerID(idMazoMod);
                    if (mazoMod != null) {
                        mazoDAO.actualitzarMazo(mazoMod);
                    } else {
                        System.out.println("Mazo no trobat.");
                    }
                    break;

                case "10":
                    System.out.println("\n-- Eliminar mazo --");
                    System.out.print("Introdueix ID del mazo a eliminar: ");
                    int idMazoElim = Integer.parseInt(s.nextLine());
                    Mazo mazoElim = mazoDAO.obtenirMazoPerID(idMazoElim);
                    if (mazoElim != null) {
                        System.out.println(mazoElim);
                        mazoDAO.eliminarMazo(mazoElim);
                    } else {
                        System.out.println("Mazo no trobat.");
                    }
                    break;

                case "0":
                    System.out.println("Fins aviat!");
                    BDOOUtil.tancarConexio();
                    executant = false;
                    break;

                default:
                    System.out.println("Opció no vàlida, torna a provar.");
                    break;
            }
        }

        s.close();

    }
}
