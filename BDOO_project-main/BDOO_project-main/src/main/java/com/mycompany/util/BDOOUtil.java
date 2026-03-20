package com.mycompany.bdoo;

import java.io.*;
import com.mycompany.dao.CartaDAO;
import com.mycompany.dao.CriaturaDAO;
import com.mycompany.dao.EncanteriDAO;
import com.mycompany.dao.JugadorDAO;
import com.mycompany.dao.MazoDAO;
import com.mycompany.dao.TerraDAO;
import com.mycompany.model.Carta;
import com.mycompany.model.Criatura;
import com.mycompany.model.Encanteri;
import com.mycompany.model.Jugador;
import com.mycompany.model.Mazo;
import com.mycompany.model.Terra;
import com.mycompany.util.BDOOUtil;
import com.mycompany.util.ImportadorDeCartes;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class BDOO {

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[30m";

    public static void main(String[] args) throws IOException {

        EncanteriDAO encDAO = new EncanteriDAO();
        TerraDAO tDAO = new TerraDAO();
        CriaturaDAO crDAO = new CriaturaDAO();
        CartaDAO cDAO = new CartaDAO();
        MazoDAO mazoDAO = new MazoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        Scanner s = new Scanner(System.in);

        buidarBaseDades();
        


        System.out.println("-- POLY-DECK ENGINE: Magic The Gathering --");

        boolean executant = true;

        while (executant) {

            System.out.println(ANSI_RED + "===== MENÚ PRINCIPAL =====");
            System.out.println(ANSI_YELLOW + "-- Cartes --");
            System.out.println("1. Crear carta");
            System.out.println("2. Veure totes les cartes");
            System.out.println("3. Veure carta per ID");
            System.out.println("4. Modificar carta");
            System.out.println("5. Eliminar carta");
            System.out.println(ANSI_YELLOW + "-- Mazos --");
            System.out.println("6. Crear mazo");
            System.out.println("7. Veure tots els mazos");
            System.out.println("8. Veure mazo per ID");
            System.out.println("9. Modificar mazo");
            System.out.println("10. Eliminar mazo");
            System.out.println(ANSI_YELLOW + "-- Jugadors --");
            System.out.println("11. Crear jugador");
            System.out.println("12. Veure tots els jugadors");
            System.out.println("13. Veure jugador per ID");
            System.out.println("14. Modificar jugador");
            System.out.println("15. Eliminar jugador");
            System.out.println(ANSI_YELLOW + "-- Consultes --");
            System.out.println(ANSI_YELLOW + "----Extra-----");
            System.out.println("16. Importar cartes des de fitxer");
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
                            crDAO.crearCriatura();
                            break;
                        case "3":
                            tDAO.crearTerra();
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
                    if (cv != null) {
                        System.out.println(cv);
                    }
                    break;

                case "4":
                    System.out.println("-- Modificar carta --");
                    System.out.println("De quin tipus?");
                    System.out.println("[1] Encanteri");
                    System.out.println("[2] Criatura");
                    System.out.println("[3] Terra");
                    System.out.print("Selecciona: ");

                    String tipusMod = s.nextLine();

                    if (tipusMod.matches("[0-9]+")) {

                        System.out.print("Introdueix ID de la carta a modificar: ");
                        boolean idValid = false;
                        int idm = 0;

                        do {
                            try {
                                System.out.print("Introdueix ID: ");
                                idm = Integer.parseInt(s.nextLine());
                                idValid = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Has d'introduir un número sencer.");
                            }
                        } while (!idValid);

                        switch (tipusMod) {
                            case "1":
                                Encanteri encMod = encDAO.obtenirEncanteriPerID(idm);
                                if (encMod != null) {
                                    encDAO.actualitzarEncanteri(encMod);
                                } else {
                                    System.out.println("Encanteri no trobat.");
                                }
                                break;
                            case "2":
                                Criatura criMod = crDAO.obtenirCriaturaPerID(idm);
                                if (criMod != null) {
                                    crDAO.actualitzarCriatura(criMod);
                                } else {
                                    System.out.println("Criatura no trobada.");
                                }
                                break;
                            case "3":
                                Terra terraMod = tDAO.obtenirTerraPerID(idm);
                                if (terraMod != null) {
                                    tDAO.actualitzarTerra(terraMod);
                                } else {
                                    System.out.println("Terra no trobada.");
                                }
                                break;
                            default:
                                System.out.println("Tipus no vàlid.");
                                break;
                        }
                    }
                    break;

                case "5":
                    System.out.println("-- Eliminar carta --");
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
                    System.out.println("-- Crear mazo --");
                    mazoDAO.crearMazo();
                    break;

                case "7":
                    System.out.println("-- Tots els mazos --");
                    mazoDAO.obtenirTotsElsMazos();
                    break;

                case "8":
                    System.out.println("-- Veure mazo per ID --");
                    System.out.print("Introdueix ID: ");
                    int idMazoVeure = Integer.parseInt(s.nextLine());
                    Mazo mazoVeure = mazoDAO.obtenirMazoPerID(idMazoVeure);
                    if (mazoVeure != null) {
                        System.out.println(mazoVeure);
                    }
                    break;

                case "9":
                    System.out.println("-- Modificar mazo --");
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
                    System.out.println("-- Eliminar mazo --");
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

                case "11":
                    System.out.println("-- Crear jugador --");
                    jugadorDAO.crearJugador();
                    break;

                case "12":
                    System.out.println("-- Tots els jugadors --");
                    jugadorDAO.obtenirTotsElsJugadors();
                    break;

                case "13":
                    System.out.println("-- Veure jugador per ID --");
                    System.out.print("Introdueix ID: ");
                    int idJugadorVeure = Integer.parseInt(s.nextLine());
                    Jugador jugadorVeure = jugadorDAO.obtenirJugadorPerID(idJugadorVeure);
                    if (jugadorVeure != null) {
                        System.out.println(jugadorVeure);
                    }
                    break;

                case "14":
                    System.out.println("-- Modificar jugador --");
                    System.out.print("Introdueix ID del jugador a modificar: ");
                    int idJugadorMod = Integer.parseInt(s.nextLine());
                    Jugador jugadorMod = jugadorDAO.obtenirJugadorPerID(idJugadorMod);
                    if (jugadorMod != null) {
                        jugadorDAO.actualitzarJugador(jugadorMod);
                    } else {
                        System.out.println("Jugador no trobat.");
                    }
                    break;

                case "15":
                    System.out.println("-- Eliminar jugador --");
                    System.out.print("Introdueix ID del jugador a eliminar: ");
                    int idJugadorElim = Integer.parseInt(s.nextLine());
                    Jugador jugadorElim = jugadorDAO.obtenirJugadorPerID(idJugadorElim);
                    if (jugadorElim != null) {
                        System.out.println(jugadorElim);
                        jugadorDAO.eliminarJugador(jugadorElim);
                    } else {
                        System.out.println("Jugador no trobat.");
                    }
                    break;
                    
                case "16":
                    
                    System.out.println("-- Importar cartes des de fitxer --");
                    String ruta = BDOO.class.getClassLoader().getResource("cartes.txt").getPath();
                    
                    try {
                        
                        ImportadorDeCartes importador = new ImportadorDeCartes();
                        List<Object> cartesImportades = importador.importarCartes(ruta);
                        importarCartes(cartesImportades);
                        
                    } catch (IOException e) {
                        
                        System.out.println("ERROR: No s'ha pogut llegir el fitxer.");
                        e.printStackTrace();
                        
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
    
    

    public static void importarCartes(List<Object> cartes) {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            for (Object carta : cartes) {
                em.persist(carta);
            }
            em.getTransaction().commit();
            System.out.println(cartes.size() + " cartes importades correctament.");
        } catch (Exception e) {
            System.out.println("ERROR important les cartes.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } 
        
        em.close();
        
    }

    public static void buidarBaseDades() {
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Encanteri").executeUpdate();
            em.createQuery("DELETE FROM Criatura").executeUpdate();
            em.createQuery("DELETE FROM Terra").executeUpdate();
            em.createQuery("DELETE FROM Carta").executeUpdate();
            em.createQuery("DELETE FROM Mazo").executeUpdate();
            em.createQuery("DELETE FROM Jugador").executeUpdate();
            em.getTransaction().commit();
            System.out.println("Base de dades buidada.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        em.close();

    }
}
