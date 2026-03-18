package com.mycompany.model;

import javax.persistence.Entity;

@Entity 
public class Criatura extends Carta {

    private int forca;          
    private int resistencia;    
    private String tipusCriatura; 
    private boolean vola;       

    public Criatura() {
    }

    public Criatura(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost, int forca, int resistencia, String tipusCriatura, boolean vola) {
        super(nom, descripcio, edicio, raresa, cost); 
        this.forca = forca;
        this.resistencia = resistencia;
        this.tipusCriatura = tipusCriatura;
        this.vola = vola;
    }

    // Getters i Setters
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }

    public int getResistencia() { return resistencia; }
    public void setResistencia(int resistencia) { this.resistencia = resistencia; }

    public String getTipusCriatura() { return tipusCriatura; }
    public void setTipusCriatura(String tipusCriatura) { this.tipusCriatura = tipusCriatura; }

    public boolean isVola() { return vola; }
    public void setVola(boolean vola) { this.vola = vola; }

    public void mostrarInfo() {
        System.out.println("Nom: " + getNom());
        System.out.println("Edició: " + getEdicio());
        // Aquest mètode requereix que Carta tingui getRaresa()
        System.out.println("Raresa: " + getRaresa()); 
        System.out.println("Tipus: " + tipusCriatura);
        System.out.println("P/T: " + forca + "/" + resistencia);
        System.out.println("Vola: " + (vola ? "Sí" : "No"));
    }
}