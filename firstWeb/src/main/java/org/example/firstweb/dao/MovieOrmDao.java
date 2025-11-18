package org.example.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.ConnectionManager;

import java.util.List;

public class MovieOrmDao implements MovieDao{
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies;

        try {
            movies = em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

        return movies;
    }

    @Override
    public Movie findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();

        Movie movie = em.find(Movie.class, id);

        if (movie != null && movie.getComments() != null) {
            movie.getComments().size();
        }

        em.close();
        return movie;
    }

    @Override
    public boolean addMovie(Movie movie) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = null;
        boolean success = false;

        try {
            tx = em.getTransaction();
            tx.begin();

            em.persist(movie);

            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al agregar la película", e);
        } finally {
            em.close();
        }

        return success;
    }

    @Override
    public Movie deleteMovie(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Movie movieToDelete = em.find(Movie.class, id);

        if (movieToDelete == null) {
            em.close();
            return null;
        }

        try {
            tx.begin();
            em.remove(movieToDelete);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar la película con id " + id, e);
        } finally {
            em.close();
        }

        return movieToDelete;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = null;
        Movie updatedMovie = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            updatedMovie = em.merge(movie);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la película", e);
        } finally {
            em.close();
        }

        return updatedMovie;
    }

}

