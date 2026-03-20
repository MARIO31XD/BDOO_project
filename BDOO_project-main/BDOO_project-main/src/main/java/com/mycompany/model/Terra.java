
package com.mycompany.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.mycompany.util.ColorProduccio;

@Entity

public class Terra extends Carta {

    String tipus;

    boolean esBasica = false; // terra básica o especial
    
    ColorProduccio colorProduccio;
    

    public Terra(String nom, String descripcio, String edicio, Raresa raresa,  String tipus, ColorProduccio colorProduccio , boolean esBasica, CostMana cost ) {
        super(nom, descripcio,edicio, raresa, cost);
        this.tipus = tipus;
        this.esBasica = esBasica;
        this.colorProduccio = colorProduccio;
        this.raresa = raresa;
    }
    

    public String getTipus() {
        return tipus;
    }

    public boolean esBasica() {
        return esBasica;
    }

}

