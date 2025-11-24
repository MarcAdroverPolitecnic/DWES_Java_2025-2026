package org.example.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoOrm implements MovieDao {
    @Override
    public List<Movie> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Movie> movies = new ArrayList<>();

        try {
            movies = em.createQuery("SELECT m FROM Movie m", Movie.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return movies;
    }

    @Override
    public Movie findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        Movie movie = null;

        try {
            movie = em.find(Movie.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return movie;
    }

    @Override
    public boolean addMovie(Movie movie) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = null;
        boolean success = false;

        try {
            et = em.getTransaction();
            et.begin();

            em.persist(movie);

            et.commit();
            success = true;
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return success;
    }

    @Override
    public Movie deleteMovieById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = null;
        Movie movie = null;

        try {
            System.out.println(id);
            et = em.getTransaction();
            et.begin();

            movie = em.find(Movie.class, id);
            if (movie != null) {
                em.remove(movie);
            }

            et.commit();
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return movie;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        EntityManager em = ConnectionManager.getEntityManager();
        Movie movieUpdate;
        try {
            em.getTransaction().begin();
            movieUpdate = em.find(Movie.class, movie.getId());
            if (movieUpdate != null) {
                movieUpdate.setTitle(movie.getTitle());
                movieUpdate.setDescription(movie.getDescription());
                movieUpdate.setYear(movie.getYear());

                em.merge(movieUpdate);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar la película con ID " + movie.getId(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Comment> findCommentsByMovieId(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Comment c WHERE c.movie.id = :id ORDER BY c.created_at DESC",
                            Comment.class
                    )
                    .setParameter("id", id)
                    .getResultList();

        } finally {
            em.close();
        }
    }
}
