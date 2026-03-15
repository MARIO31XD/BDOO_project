package com.mycompany.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Carta {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String descripcio;
    private String edicio;

    @Enumerated(EnumType.STRING)
    private Raresa raresa;

    @Embedded
    private CostMana cost;

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