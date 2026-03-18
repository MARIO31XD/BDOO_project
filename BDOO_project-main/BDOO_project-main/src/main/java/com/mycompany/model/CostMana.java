package com.mycompany.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CostMana implements Serializable {
    private int blanc, blau, negre, vermell, verd, incolor;

    public CostMana() {}

    public CostMana(int blanc, int blau, int negre, int vermell, int verd, int incolor) {
        this.blanc = blanc;
        this.blau = blau;
        this.negre = negre;
        this.vermell = vermell;
        this.verd = verd;
        this.incolor = incolor;
    }

    public int getBlanc() { return blanc; }
    public int getBlau() { return blau; }
    public int getNegre() { return negre; }
    public int getVermell() { return vermell; }
    public int getVerd() { return verd; }
    public int getIncolor() { return incolor; }
}