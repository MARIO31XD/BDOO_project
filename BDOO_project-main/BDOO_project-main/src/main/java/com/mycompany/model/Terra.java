
package com.mycompany.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Terra extends Carta {
   
    String tipus;

    boolean esBasica = false; // terra básica o especial
    
    public ColorProduccio colorProduccio; // color de produccio de mana

    public enum ColorProduccio {  // color de produccio de mana
        VERMELL, BLAU, VERD
    }
    
    public Terra(String nom, String descripcio, String edicio, String tipus, Raresa raresa, CostMana cost, ColorProduccio colorProduccio , boolean esBasica) {
        super(nom, descripcio, edicio, raresa, cost);
        this.tipus = tipus;
        this.esBasica = esBasica;
        this.colorProduccio = colorProduccio;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " Terra: " + getNom() +
               " | Tipus: " + tipus +
               " | Color de producció: " + colorProduccio +
               " | Bàsica: " + esBasica;
    }

    public String getTipus() {
        return tipus;
    }

    public boolean esBasica() {
        return esBasica;
    }

    public boolean isEsBasica() {
        return esBasica;
    }

    public ColorProduccio getColorProduccio() {
        return colorProduccio;
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

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setEsBasica(boolean esBasica) {
        this.esBasica = esBasica;
    }

    public void setColorProduccio(ColorProduccio colorProduccio) {
        this.colorProduccio = colorProduccio;
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

    public void setRaresa(Raresa raresa) {
        this.raresa = raresa;
    }

    public void setCost(CostMana cost) {
        this.cost = cost;
    }
   

}
