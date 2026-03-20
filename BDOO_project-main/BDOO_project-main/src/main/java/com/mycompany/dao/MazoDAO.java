package com.mycompany.dao;

import com.mycompany.model.Carta;
import com.mycompany.model.Mazo;
import com.mycompany.util.BDOOUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MazoDAO {

    public void crearMazo() {

        Scanner s = new Scanner(System.in);
        CartaDAO cartaDAO = new CartaDAO();

        System.out.println("Introdueix nom del mazo:");
        String nom = s.nextLine();

        // crear mazo con fecha actual
        Mazo mazo = new Mazo(nom, LocalDate.now());

        //inicializar arraylist de cartas
        mazo.setCartes(new ArrayList<>());

        //ir añadiendo cartas al mazo
        System.out.println("Afegir cartes al mazo. Escriu [sortir] per acabar.");
        boolean continuar = true;
        String keyword = "sortir";

        while (continuar) {

            String resposta = s.nextLine().toLowerCase();

            if (resposta.equals(keyword.toLowerCase())) {
                continuar = false;
                //comproba que la resposta sigui un numero
            } else if (resposta.matches("[0-9]+") && resposta.length() >= 1) {

                int idCarta = Integer.parseInt(resposta);

                Carta carta = cartaDAO.obtenirCartaPerID(idCarta, Carta.class);

                if (carta != null) {
                    mazo.afegirCarta(carta);
                    System.out.println("Carta afegida al mazo.");
                } else {
                    System.out.println("Carta no trobada.");
                }

            } else {
                System.out.println("Resposta incorrecta, torna a escriure el ID o sortir.");
            }
        }

        // guardar i ppersistir
        EntityManager em = BDOOUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(mazo);
            em.getTransaction().commit();
            System.out.println("Mazo creat.");
        } catch (Exception e) {
            System.out.println("ERROR creant el mazo.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Mazo obtenirMazoPerID(int id) {

        EntityManager em = BDOOUtil.getEntityManager();
        try {
            // join fetch dice a jpa que cargue las cartas en la query antes de cerrar el em para que aparezcan y no diga que no tiene cartas
            //distinct para no duplicar filas
        TypedQuery<Mazo> query = em.createQuery(
                    "SELECT DISTINCT m FROM Mazo m LEFT JOIN FETCH m.cartes WHERE m.id = :id", Mazo.class);
                query.setParameter("id", (long) id);
                return query.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                System.out.println("No s'ha trobat cap mazo amb ID " + id);
                return null;

        } finally {
            em.close();
        }

    }

    public void obtenirTotsElsMazos() {
        EntityManager em = BDOOUtil.getEntityManager();
        TypedQuery<Mazo> query = em.createQuery("SELECT m FROM Mazo m", Mazo.class);
        List<Mazo> resultats = query.getResultList();
        for (Mazo m : resultats) {
            System.out.println(m);
        }
        em.close();
    }

    public void actualitzarMazo(Mazo mazo) {

        Scanner s = new Scanner(System.in);
        CartaDAO cartaDAO = new CartaDAO();
        boolean continuar = true;
        String keyword = "sortir";

        System.out.println("Nom: [" + mazo.getNom() + "]");
        String nom = s.nextLine();
        mazo.setNom(nom);

        //ir añadiendo cartas al mazo
        System.out.println("Afegir cartes al mazo. Escriu [sortir] per acabar.");

        //las cartas se guardan en una lista temporal
        List<Carta> cartasAAnadir = new ArrayList<>();

        while (continuar) {

            String resposta = s.nextLine().toLowerCase();

            if (resposta.equals(keyword.toLowerCase())) {
                continuar = false;
                //comproba que la resposta sigui un numero
            } else if (resposta.matches("[0-9]+") && resposta.length() >= 1) {

                int idCarta = Integer.parseInt(resposta);

                Carta carta = cartaDAO.obtenirCartaPerID(idCarta, Carta.class);

                if (carta != null) {
                    cartasAAnadir.add(carta);
                    System.out.println("Carta afegida al mazo.");
                } else {
                    System.out.println("Carta no trobada.");
                }

            } else {
                System.out.println("Resposta incorrecta, torna a escriure el ID o sortir.");
            }
        }

        // guardar
        EntityManager em = BDOOUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // hacer merge de cada carta dentro del entity manager
            for (Carta carta : cartasAAnadir) {
                Carta cartaManaged = em.merge(carta);
                //y se añaden al mazo
                mazo.afegirCarta(cartaManaged);
            }

            em.merge(mazo);
            em.getTransaction().commit();
            System.out.println("Mazo actualitzat.");
        } catch (Exception e) {
            System.out.println("Errpor, mazo no actualitzat.");
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarMazo(Mazo mazo) {

        EntityManager em = BDOOUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Mazo managedMazo = em.find(Mazo.class, mazo.getId());

            if (managedMazo != null) {
                em.remove(managedMazo);
                em.getTransaction().commit();
                System.out.println("Mazo eliminat.");
            } else {
                em.getTransaction().rollback();
            }

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
