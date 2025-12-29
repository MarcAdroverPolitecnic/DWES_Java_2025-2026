package org.example.blackjack.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionManager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("blackjack");

    private ConnectionManager(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
