package com.mycompany.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Carta {

    @Id
    @GeneratedValue
    public Long id;

    public String nom;
    public String descripcio;
    public String edicio;

    @Enumerated(EnumType.STRING)
    public Raresa raresa;

    @Embedded
    public CostMana cost;

    // Constructor necesario para JPA.
    public Carta() {}

    public Carta(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.edicio = edicio;
        this.raresa = raresa;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getEdicio() {
        return edicio;
    }

    public Raresa getRaresa() {
        return raresa;
    }

    public CostMana getCost() {
        return cost;
    }
}
