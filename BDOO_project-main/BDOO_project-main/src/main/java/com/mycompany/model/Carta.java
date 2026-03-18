package com.mycompany.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Carta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nom;
    protected String descripcio;
    protected String edicio;

    @Enumerated(EnumType.STRING)
    protected Raresa raresa;

    @Embedded
    protected CostMana cost;

    public Carta() {}

    public Carta(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost) {
        this.nom = nom;
        this.descripcio = descripcio;
        this.edicio = edicio;
        this.raresa = raresa;
        this.cost = cost;
    }

    public String getNom() { return nom; }
    public String getEdicio() { return edicio; }
    public Raresa getRaresa() { return raresa; }
}