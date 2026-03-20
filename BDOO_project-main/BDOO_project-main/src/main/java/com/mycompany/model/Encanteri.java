package com.mycompany.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "encanteri_id")
public class Encanteri extends Carta {
    
    String tipus; //aura, malediccio, global
    boolean esInstantani;
    CostMana cost;
    

    public Encanteri(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost, String tipus, boolean esInstantani) {
        super(nom, descripcio, edicio, raresa, cost);
        this.tipus = tipus;
        this.esInstantani = esInstantani;
        this.raresa = raresa;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Encanteri:  Nom: " + nom +  "Descripcio: " + descripcio + " | Tipus: " + tipus + " | Es Instantani?: " + esInstantani + "Cost de mana: " + cost.toString() + "Raresa: " + raresa + " Edicio: " + edicio;
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

    public void setCost(CostMana cost) {
        this.cost = cost;
    }

    public void setRaresa(Raresa raresa) {
        this.raresa = raresa;
    }

    
}
