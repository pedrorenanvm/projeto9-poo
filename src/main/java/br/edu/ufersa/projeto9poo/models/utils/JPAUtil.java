package br.edu.ufersa.projeto9poo.models.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static volatile JPAUtil instance;
    private static final String PERSISTENCE_UNIT_NAME = "acaiteria";
    private final EntityManagerFactory emf;

    private JPAUtil() {
        System.out.println("Executando o construtor do JPAUtil");
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private static JPAUtil pegarInstancia() {
        if (instance == null) {
            synchronized (JPAUtil.class) {
                if (instance == null) {
                    instance = new JPAUtil();
                }
            }
        }
        return instance;
    }

    public static EntityManager pegarEntityManagerFactory() {
        return pegarInstancia().emf.createEntityManager();
    }

    public static void shutdown() {
        EntityManagerFactory emf = pegarInstancia().emf;
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
