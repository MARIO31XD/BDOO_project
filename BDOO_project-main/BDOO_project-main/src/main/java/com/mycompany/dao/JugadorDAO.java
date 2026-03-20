package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.Jugador;
import com.mycompany.model.Mazo;
import com.mycompany.util.BDOOUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class JugadorDAO {

    // CREATE
    public void crearJugador() {

        Scanner s = new Scanner(System.in);
        CartaDAO cartaDAO = new CartaDAO();
        MazoDAO mazoDAO = new MazoDAO();

        System.out.println("Introdueix nick:");
        String nick = s.nextLine();

        System.out.println("Introdueix nivell:");
        int nivell = 0;
        
        boolean continuar = false;
        
        do {
            try {
                
                nivell = Integer.parseInt(s.nextLine());
                continuar = true;
                
            } catch (NumberFormatException e) {
                System.out.println("Has d'introduir un número sencer.");
            }
            
        } while (!continuar);

        Jugador jugador = new Jugador(nick, nivell);

        // afegir cartes a la col·leccio
        System.out.println("Afegir cartes a la col·leccio. Escriu [sortir] per acabar.");
        
        continuar = true;
        
        while (continuar) {
            
            String resposta = s.nextLine().toLowerCase();
            
            if (resposta.equals("sortir")) {
                continuar = false;
                
            } else if (resposta.matches("[0-9]+")) {
                
                int idCarta = Integer.parseInt(resposta);
                Carta carta = cartaDAO.obtenirCartaPerID(idCarta, Carta.class);
                
                if (carta != null) {
                    jugador.afegirCarta(carta);
                    System.out.println("Carta afegida a la col·leccio.");
                } else {
                    System.out.println("Carta no trobada.");
                }
                
            } else {
                System.out.println("Resposta incorrecta, escriu un ID o sortir.");
            }
        }

        // afegir mazos al jugador
        System.out.println("Afegir mazos al jugador. Escriu [sortir] per acabar.");
        
        continuar = true;
        
        while (continuar) {
            
            String resposta = s.nextLine().toLowerCase();
            
            if (resposta.equals("sortir")) {
                continuar = false;
                
            } else if (resposta.matches("[0-9]+")) {
                
                int idMazo = Integer.parseInt(resposta);
                Mazo mazo = mazoDAO.obtenirMazoPerID(idMazo);
                
                if (mazo != null) {
                    jugador.afegirMazo(mazo);
                    System.out.println("Mazo afegit al jugador.");
                } else {
                    System.out.println("Mazo no trobat.");
                }
                
            } else {
                System.out.println("Resposta incorrecta, escriu un ID o sortir.");
            }
        }

        // persistir
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            
            em.getTransaction().begin();

            // merge de cartes per evitar duplicate key
            List<Carta> cartesManaged = new ArrayList<>();
            
            for (Carta carta : jugador.getColleccio()) {
                cartesManaged.add(em.merge(carta));
            }
            
            jugador.setColleccio(cartesManaged);

            // merge de mazos per evitar duplicate key
            List<Mazo> mazosManaged = new ArrayList<>();
            
            for (Mazo mazo : jugador.getMazos()) {
                mazosManaged.add(em.merge(mazo));
            }
            
            jugador.setMazos(mazosManaged);

            em.persist(jugador);
            em.getTransaction().commit();
            System.out.println("Jugador creat.");
            
        } catch (Exception e) {
            
            System.out.println("ERROR creant el jugador.");
            
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            
        } finally {
            em.close();
        }
    }

    // READ - per ID
    public Jugador obtenirJugadorPerID(int id) {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            
            TypedQuery<Jugador> query = em.createQuery(
                "SELECT DISTINCT j FROM Jugador j " +
                "LEFT JOIN FETCH j.mazos " +
                "LEFT JOIN FETCH j.coleccio " +
                "WHERE j.id = :id", Jugador.class);
            query.setParameter("id", id);
            return query.getSingleResult();
            
        } catch (NoResultException e) {
            
            System.out.println("No s'ha trobat cap jugador amb ID " + id);
            return null;
            
        } finally {
            em.close();
        }
        
    }

    // READ - tots
    public void obtenirTotsElsJugadors() {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            
            TypedQuery<Jugador> query = em.createQuery(
                "SELECT DISTINCT j FROM Jugador j " +
                "LEFT JOIN FETCH j.mazos " +
                "LEFT JOIN FETCH j.coleccio", Jugador.class);
            List<Jugador> resultats = query.getResultList();
            for (Jugador j : resultats) {
                System.out.println(j);
            }
            
        } finally {
            em.close();
        }
    }

    // UPDATE
    public void actualitzarJugador(Jugador jugador) {

        Scanner s = new Scanner(System.in);
        CartaDAO cartaDAO = new CartaDAO();
        MazoDAO mazoDAO = new MazoDAO();

        System.out.println("Nick: [" + jugador.getNick() + "]");
        String nick = s.nextLine();
        jugador.setNick(nick);

        System.out.println("Nivell: [" + jugador.getNivell() + "]");
        boolean continuar = false;
        do {
            try {
                
                int nivell = Integer.parseInt(s.nextLine());
                jugador.setNivell(nivell);
                continuar = true;
                
            } catch (NumberFormatException e) {
                System.out.println("Has d'introduir un número sencer.");
            }
            
        } while (!continuar);

        // afegir cartes a la col·leccio
        System.out.println("Afegir cartes a la col·leccio. Escriu [sortir] per acabar.");
        
        List<Carta> cartesNoves = new ArrayList<>();
        
        continuar = true;
        
        while (continuar) {
            
            String resposta = s.nextLine().toLowerCase();
            
            if (resposta.equals("sortir")) {
                continuar = false;
                
            } else if (resposta.matches("[0-9]+")) {
                
                int idCarta = Integer.parseInt(resposta);
                Carta carta = cartaDAO.obtenirCartaPerID(idCarta, Carta.class);
                if (carta != null) {
                    cartesNoves.add(carta);
                    System.out.println("Carta afegida.");
                } else {
                    System.out.println("Carta no trobada.");
                }
                
            } else {
                
                System.out.println("Resposta incorrecta, escriu un ID o sortir.");
            }
        }

        // afegir mazos
        System.out.println("Afegir mazos. Escriu [sortir] per acabar.");
        
        List<Mazo> mazosNous = new ArrayList<>();
        
        continuar = true;
        
        while (continuar) {
            
            String resposta = s.nextLine().toLowerCase();
            
            if (resposta.equals("sortir")) {
                continuar = false;
                
            } else if (resposta.matches("[0-9]+")) {
                
                int idMazo = Integer.parseInt(resposta);
                Mazo mazo = mazoDAO.obtenirMazoPerID(idMazo);
                
                if (mazo != null) {
                    
                    mazosNous.add(mazo);
                    System.out.println("Mazo afegit.");
                    
                } else {
                    
                    System.out.println("Mazo no trobat.");
                    
                }
            } else {
                
                System.out.println("Resposta incorrecta, escriu un ID o sortir.");
                
            }
        }

        // persistir
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            
            em.getTransaction().begin();

            for (Carta carta : cartesNoves) {
                jugador.afegirCarta(em.merge(carta));
            }
            for (Mazo mazo : mazosNous) {
                jugador.afegirMazo(em.merge(mazo));
            }

            em.merge(jugador);
            em.getTransaction().commit();
            System.out.println("Jugador actualitzat.");
            
        } catch (Exception e) {
            
            System.out.println("ERROR actualitzant el jugador.");
            
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            
            e.printStackTrace();
            
        } finally {
            em.close();
        }
    }

    // DELETE
    public void eliminarJugador(Jugador jugador) {
        
        EntityManager em = BDOOUtil.getEntityManager();
        
        try {
            
            em.getTransaction().begin();
            
            // CascadeType.ALL s'encarrega d'eliminar els mazos del jugador
            Jugador managed = em.find(Jugador.class, jugador.getId());
            
            if (managed != null) {
                
                em.remove(managed);
                em.getTransaction().commit();
                System.out.println("Jugador eliminat.");
                
            } else {
                
                System.out.println("Jugador no trobat.");
                em.getTransaction().rollback();
                
            }
        } catch (Exception e) {
            
            System.out.println("ERROR eliminant el jugador.");
            
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            
            e.printStackTrace();
            
        } finally {
            
            em.close();
        }
    }
}
