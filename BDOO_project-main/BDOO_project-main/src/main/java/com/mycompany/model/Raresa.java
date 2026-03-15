
package com.mycompany.model;


public enum Raresa {
    
    COMUNA("Comuna"),
    RARA("Rara"),
    MITICA("Mítica");

    private final String mostrarRaresa;

    Raresa(String mostrarRaresa) {
        this.mostrarRaresa = mostrarRaresa;
    }

    public String getRaresaNom() {
        return mostrarRaresa;
    }

    @Override
    public String toString() {
        return mostrarRaresa;
    }
    
}
