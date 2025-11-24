package org.example.firstweb.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.ConnectionManager;
import org.example.firstweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoJdbc implements MovieDao {
    public static final Connection connection;

    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> allMovies = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from movies");

            ResultSet result = pst.executeQuery();
            while(result.next()){
                Long movieId = result.getLong("id");
                String movieTitle = result.getString("title");
                String movieDescription = result.getString("description");
                int movieYear = result.getInt("year");

                Movie movie = new Movie(movieId, movieTitle, movieDescription, movieYear);
                allMovies.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allMovies;
    }

    @Override
    public Movie findById(Long id) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from movies where id = " + id);
            ResultSet result = pst.executeQuery();

            if(result.next()){
                return new Movie(result.getLong("id"), result.getString("title"), result.getString("description"), result.getInt("year"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addMovie(Movie movie) {
        String sql = "INSERT INTO movies (description, title, year) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, movie.getDescription());
            pst.setString(2, movie.getTitle());
            pst.setInt(3, movie.getYear());
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se insertó ninguna fila.");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie deleteMovieById(Long id) {
        Movie movie = this.findById(id);
        if (movie == null) {
            return null;
        }
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se eliminó ninguna fila con id = " + id);
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateMovie(Movie movie){
        String sql = "UPDATE movies SET " + "title = ?,description = ?,year = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, movie.getTitle());
            pst.setString(2, movie.getDescription());
            pst.setInt(3, movie.getYear());
            pst.setLong(4, movie.getId());

            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se edito la fila con id = " + movie.getId());
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findCommentsByMovieId(Long id){

        List<Comment> allComments = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from comments where movie_id = " + id + " order by created_at desc");

            ResultSet result = pst.executeQuery();
            while(result.next()){
                Long commentId = result.getLong("id");
                String commentText = result.getString("comment_text");

                Comment comment = new Comment(commentId, findById(id), commentText);
                allComments.add(comment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allComments;

    }
}
