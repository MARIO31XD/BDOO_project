package com.mycompany.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Terra extends Carta {
    private String tipus;
    private boolean esBasica;
    
    @Enumerated(EnumType.STRING)
    private ColorProduccio colorProduccio;

    public enum ColorProduccio { VERMELL, BLAU, VERD, BLANC, NEGRE, INCOLOR }

    public Terra() {}

    public Terra(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost, String tipus, ColorProduccio color, boolean esBasica) {
        super(nom, descripcio, edicio, raresa, cost);
        this.tipus = tipus;
        this.colorProduccio = color;
        this.esBasica = esBasica;
    }
}