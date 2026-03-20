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

    // Constructor 
    public Criatura(String nom, String descripcio, String edicio, Raresa raresa, CostMana cost, int forca, int resistencia, String tipusCriatura, boolean vola) {

        super(nom, descripcio, edicio, raresa, cost);

        this.forca = forca;
        this.resistencia = resistencia;
        this.tipusCriatura = tipusCriatura;
        this.vola = vola;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " Criatura: " + getNom()
                + " | Força: " + forca
                + " | Resistència: " + resistencia
                + " | Tipus: " + tipusCriatura
                + " | Vola: " + vola;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public String getTipusCriatura() {
        return tipusCriatura;
    }

    public void setTipusCriatura(String tipusCriatura) {
        this.tipusCriatura = tipusCriatura;
    }

    public boolean isVola() {
        return vola;
    }

    public void setVola(boolean vola) {
        this.vola = vola;
    }

    public void mostrarInfo() {
        System.out.println("Nom: " + getNom());
        System.out.println("Edició: " + getEdicio());
        System.out.println("Raresa: " + getRaresa());
        System.out.println("Tipus: " + tipusCriatura);
        System.out.println("P/T: " + forca + "/" + resistencia);
        System.out.println("Vola: " + (vola ? "Sí" : "No"));
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
