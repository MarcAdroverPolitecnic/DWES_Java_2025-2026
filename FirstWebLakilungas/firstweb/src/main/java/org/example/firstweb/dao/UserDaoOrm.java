package org.example.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.firstweb.model.User;
import org.example.firstweb.util.ConnectionManager;
import org.example.firstweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoOrm implements  UserDao {

    public boolean login(String username, String password) {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
        );

        query.setParameter("username", username);
        query.setParameter("password", password);

        return !query.getResultList().isEmpty();
    }



    public boolean register(User user) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
    }
}