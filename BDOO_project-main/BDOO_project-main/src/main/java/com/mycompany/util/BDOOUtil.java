package com.mycompany.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BDOOUtil {

    private static EntityManagerFactory emf = null;

    static {
        try {
            // Verify ObjectDB provider class is loadable
            Class.forName("com.objectdb.jpa.Provider");
            System.out.println("ObjectDB provider found OK");
            if (emf == null || !emf.isOpen()) {
                emf = Persistence.createEntityManagerFactory("$objectdb/db/cartes.odb");
            }
        } catch (Throwable ex) {
            System.err.println("Error al crear EntityManagerFactory: " + ex);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void tancarConexio() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
