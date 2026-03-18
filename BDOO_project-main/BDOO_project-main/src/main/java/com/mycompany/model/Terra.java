
package com.mycompany.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Terra extends Carta {

    String tipus;

    boolean esBasica = false; // terra básica o especial
    
    ColorProduccio colorProduccio; // color de produccio de mana

    enum ColorProduccio {  // color de produccio de mana
        VERMELL, BLAU, VERD
    }
    public Terra(String nom, String descripcio, String edicio, String tipus, ColorProduccio colorProduccio , boolean esBasica) {
        super(nom, descripcio, edicio);
        this.tipus = tipus;
        this.esBasica = esBasica;
        this.colorProduccio = colorProduccio;}
    

    public String getTipus() {
        return tipus;
    }

    public boolean esBasica() {
        return esBasica;
    }

}

