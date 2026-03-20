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
