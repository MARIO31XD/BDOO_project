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
    
    @ManyToMany(cascade = CascadeType.PERSIST)
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
        return "Mazo: " + "ID: " + id + " | Nom: " + nom + " | Data de creació: " + dataCreacio + " | Cartes: " + cartes;
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
