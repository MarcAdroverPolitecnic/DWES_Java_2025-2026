package org.example.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.example.firstweb.model.User;
import org.example.firstweb.util.ConnectionManager;

public class UserOrm {

    public User findUserByUsername(String username) {
        EntityManager em = ConnectionManager.getEntityManager();
        User user = null;
        try {
            user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = null;
        } finally {
            em.close();
        }
        return user;
    }

    public boolean addUser(User user) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = null;
        boolean success = false;

        try {
            tx = em.getTransaction();
            tx.begin();

            em.persist(user);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return success;
    }
}