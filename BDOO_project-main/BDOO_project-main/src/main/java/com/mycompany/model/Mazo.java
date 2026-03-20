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
