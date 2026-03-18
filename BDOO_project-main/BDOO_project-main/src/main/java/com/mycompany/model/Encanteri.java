package com.mycompany.model;

import javax.persistence.Entity;

@Entity
public class Encanteri extends Carta {
    private String tipus;
    private boolean esPermanent;

    public Encanteri() {}

    public Encanteri(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost, String tipus, boolean esPermanent) {
        super(nom, descripcio, edicio, raresa, cost);
        this.tipus = tipus;
        this.esPermanent = esPermanent;
    }

    // Getters y Setters
    public String getTipus() { return tipus; }
    public boolean isEsPermanent() { return esPermanent; }
}