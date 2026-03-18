
package com.mycompany.util;

import java.io.Serializable;

/**
 *
 * @author alumnet
 */
public class ColorProduccio implements Serializable{
    
    private int blanc, blau, negre, vermell, verd, incolor;
    
    public ColorProduccio() {}
    
    public ColorProduccio(int blanc,int blau,int negre,int vermell,int verd, int incolor) {
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
