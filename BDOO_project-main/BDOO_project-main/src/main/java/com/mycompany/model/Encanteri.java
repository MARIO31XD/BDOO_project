package com.mycompany.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "encanteri_id")
public class Encanteri extends Carta {
    
    String tipus; //aura, malediccio, global
    boolean esInstantani;
    CostMana cost;
    

    public Encanteri(String nom, String descripcio, Raresa raresa, CostMana cost, String tipus, boolean esInstantani) {
        super(nom, descripcio);
        this.tipus = tipus;
        this.esInstantani = esInstantani;
        this.raresa = raresa;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Encanteri: " + " | Tipus: " + tipus + " | Es Instantani?: " + esInstantani;
    }
    
    public String getTipus() {
        return tipus;
    }

    public boolean isEsInstantani() {
        return esInstantani;
    }

    public Long getId() {
        return id;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setEsInstantani(boolean esInstantani) {
        this.esInstantani = esInstantani;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setEdicio(String edicio) {
        this.edicio = edicio;
    }
    
}
