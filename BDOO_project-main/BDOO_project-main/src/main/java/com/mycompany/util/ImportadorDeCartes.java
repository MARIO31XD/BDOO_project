package com.mycompany.util;
import com.mycompany.model.CostMana;
import java.io.*;
import java.util.*;

import com.mycompany.model.Encanteri;
import com.mycompany.model.Raresa;

public class ImportadorDeCartes {
    
    public List<Object> importarCartes(String rutaArxiu) throws IOException {
        List<Object> cartes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArxiu))) {
            String linia;

            //mientras haya lineas
            while ((linia = br.readLine()) != null) {
                //obtiene la linea
                linia = linia.trim();

                //saltar lineas vacias y comentarios
                if (linia.isEmpty() || linia.startsWith("#")) continue;
                //obtener array de string con las palabras
                //se divide con |
                String[] parts = linia.split("\\|");
                //comprobacion para saber si la linea tiene solo un elemento o 0
                //si tiene 1 o 0 salta la linea
                if (parts.length < 2) continue;

                //obtener tipo (criatura, tierra, hechizo...)
                String tipus = parts[0].trim();

                //segun el tipo de carta, se parseara de una manera u otra
                switch (tipus) {
                    //case "CRIATURA" -> cartes.add(parseCriatura(parts));
                    //case "TERRA"    -> cartes.add(parseTerra(parts));
                    case "ENCANTERI"-> cartes.add(parseEncanteri(parts));
                    default -> System.err.println("Tipus desconegut: " + tipus);
                }
            }
        }

        //devuelve la lista de cartas con todas mezcladas
        return cartes;
    }
/*
    // CRIATURA
    // [0]TIPUS | [1]NOM | [2]DESC | [3]RARESA | [4]COST | [5]FORÇA | [6]RES | [7]TIPUS_CRIATURA | [8]VOLA
    private Criatura parseCriatura(String[] p) {
        String nom = p[1].trim();
        String descripcio = p[2].trim();
        Raresa raresa = Raresa.valueOf(p[3].trim());
        CostMana cost = parseCostMana(p[4].trim());
        int forca = Integer.parseInt(p[5].trim());
        int resistencia = Integer.parseInt(p[6].trim());
        String tipusCr = p[7].trim();
        boolean vola = Boolean.parseBoolean(p[8].trim());
    
        String edicio = "No esta al txt";

        return new Criatura(nom, descripcio, raresa, cost, forca, resistencia, tipusCr, vola);
    }

    // TERRA
    // [0]TIPUS | [1]NOM | [2]DESC | [3]RARESA | [4]COLOR_PRODUCCIO | [5]ES_BASICA
    private Terra parseTerra(String[] p) {
        String nom = p[1].trim();
        String descripcio = p[2].trim();
        Raresa raresa = Raresa.valueOf(p[3].trim());
        ColorMana color = ColorMana.valueOf(p[4].trim());
        boolean esBasica = Boolean.parseBoolean(p[5].trim());
    
        String edicio = "No esta al txt";

        return new Terra(nom, descripcio, raresa, color, esBasica);
    }
*/
    // ENCANTERI 
    // [0]TIPUS | [1]NOM | [2]DESC | [3]RARESA | [4]COST | [5]TIPUS_ENC | [6]ES_INSTANTANI
    private Encanteri parseEncanteri(String[] p) {
        String nom = p[1].trim();
        String descripcio = p[2].trim();
        Raresa raresa = Raresa.valueOf(p[3].trim());
        CostMana cost  = parseCostMana(p[4].trim());
        String tipusEnc = p[5].trim();
        boolean esInstantani = Boolean.parseBoolean(p[6].trim());
        
        String edicio = "No esta al txt";

        return new Encanteri(nom, descripcio, edicio, raresa, cost, tipusEnc, esInstantani);
    }

    // COST MANA
    //blau negre vermell verd blanc incolor
    private CostMana parseCostMana(String costs) {
        // pueden costar varios tipos de mana asi que se separan por comas
        //v es de valor
        String[] v = costs.split(",");
        return new CostMana(
            //convierte el valor de cada numero a int
            Integer.parseInt(v[0].trim()),  //blau
            Integer.parseInt(v[1].trim()),  //negre
            Integer.parseInt(v[2].trim()),  //vermell
            Integer.parseInt(v[3].trim()),  //verd
            Integer.parseInt(v[4].trim()),  //blanc
            Integer.parseInt(v[5].trim())   //incolor
        );
    }
}