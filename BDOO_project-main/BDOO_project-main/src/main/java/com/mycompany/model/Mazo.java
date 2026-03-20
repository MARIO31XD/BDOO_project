package com.mycompany.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Mazo {

    @Id
    @GeneratedValue
    Long id;

    String nom;
    LocalDate dataCreacio;

    @ManyToMany
    List<Carta> cartes;

    public Mazo(String nom, LocalDate dataCreacio) {
        this.nom = nom;
        this.dataCreacio = dataCreacio;
    }

    //afegir una carta
    public void afegirCarta(Carta carta) {
        this.cartes.add(carta);
    }

    @Override
    public String toString() {
        return "Mazo: " + "ID: " + id + " | Nom: " + nom + " | Data de creació: " + dataCreacio + " | Cartes: \n" + imprimirLlistaCartes();
    }

    public String imprimirLlistaCartes() {
        //si esta vacio...
        if (cartes == null || cartes.isEmpty()) {
            return "El mazo no té cartes.";
        }
        //stringbuilder para juntar todos los nombres
        StringBuilder sb = new StringBuilder();
        
        for (Carta carta : cartes) {
            sb.append("  - ID: ").append(carta.getId())
                    .append(" | Nom: ").append(carta.getNom())
                    .append(" | Tipus: ").append(carta.getClass().getSimpleName()) //pillar el tipo de carta que es
                    .append("\n");
        }
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDataCreacio() {
        return dataCreacio;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDataCreacio(LocalDate dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public List<Carta> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carta> cartes) {
        this.cartes = cartes;
    }

}
package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Jugador {

    @Id
    @GeneratedValue
    Long id;

    String nick;
    int nivell;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Mazo> mazos;

    @ManyToMany
    List<Carta> coleccio;

    public Jugador(String nick, int nivell) {
        this.nick = nick;
        this.nivell = nivell;
        this.mazos = new ArrayList<>();
        this.coleccio = new ArrayList<>();
    }

    // afegir mazo al jugador
    public void afegirMazo(Mazo mazo) {
        this.mazos.add(mazo);
    }

    // treure mazo del jugador (orphanremoval l'eliminara de la b)
    public void treureMazo(Mazo mazo) {
        this.mazos.remove(mazo);
    }

    // afegir carta a la col·leccio
    public void afegirCarta(Carta carta) {
        this.coleccio.add(carta);
    }

    // treure carta de la col·leccio
    public void treureCartaColleccio(Carta carta) {
        this.coleccio.remove(carta);
    }

    @Override
    public String toString() {
        return "Jugador: ID: " + id +
               " Nick: " + nick +
               " Nivell: " + nivell +
               " Mazos: " + mazos.size() +
               " Cartes a la col·leccio: " + coleccio.size();
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) {
        this.id = id; 
    }
    
    public String getNick() {
        return nick; 
    }
    
    public void setNick(String nick) {
        this.nick = nick; 
    }
    
    public int getNivell() {
        return nivell; 
    }
    
    public void setNivell(int nivell) {
        this.nivell = nivell;
    }
    
    public List<Mazo> getMazos() {
        return mazos;
    }
    
    public void setMazos(List<Mazo> mazos) {
        this.mazos = mazos; 
    }
    
    public List<Carta> getColleccio() {
        return coleccio; 
    }
    
    public void setColleccio(List<Carta> coleccio) {
        this.coleccio = coleccio; 
    }
}
