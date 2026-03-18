package com.mycompany.bdoo;

import com.mycompany.dao.CartaDAO;
import java.util.Scanner;


public class BDOO {

    public static void main(String[] args) {
        
        CartaDAO cartaDAO = new CartaDAO();
        Scanner s = new Scanner(System.in);
        
        System.out.println("Crear carta:");
        System.out.println("Nom:");
        String nom = s.nextLine();
        
        System.out.println("Descripcio:");
        String descripcio = s.nextLine();
        
        System.out.println("Edicio");
        String edicio = s.nextLine();
        
        
        cartaDAO.crearCarta(nom, descripcio, edicio);
        
        
    }
}
