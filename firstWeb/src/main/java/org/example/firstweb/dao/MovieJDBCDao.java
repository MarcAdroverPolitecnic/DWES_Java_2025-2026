package org.example.firstweb.dao;

import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieJDBCDao implements MovieDao {

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();

        try {
            //Connecta amb la base de dades i executa la query
            Connection conn = JdbcConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM movies");

            ResultSet result = pst.executeQuery();

            //Per cada fila del resultat, crea un objecte Movie
            while (result.next()) {
                Long movieId = result.getLong("id");
                String title = result.getString("title");
                String description = result.getString("description");
                Long year = result.getLong("year");

                Movie novaMovie = new Movie(movieId, title, description, year);
                movies.add(novaMovie);
            }

            pst.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Retorna una llista de objectes Movie
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        try {
            //Conecta a la base de dades i agafa totes les coincidencies amb la id especificada
            Connection conn = JdbcConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
            pst.setLong(1, id);

            ResultSet result = pst.executeQuery();

            //Agafa les dades i crea un objecte Movie amb els seus comentaris
            if (result.next()) {
                Long movieId = result.getLong("id");
                String movieTitle = result.getString("title");
                String movieDescription = result.getString("description");
                Long movieYear = result.getLong("year");

                Movie movie = new Movie(movieId, movieTitle, movieDescription, movieYear);
                movie.setComments(findCommentsByMovieId(conn, movieId));

                pst.close();
                conn.close();

                return movie;
            } else {
                pst.close();
                conn.close();
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Obté tots els comentaris associats a la película especificada
    private List<Comment> findCommentsByMovieId(Connection conn, Long movieId) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE movie_id = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, movieId);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setCommentText(rs.getString("comment_text"));
            comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            comments.add(comment);
        }

        pst.close();
        return comments;
    }

    //Afegeix una pelicula a la db amb les dades especificades al formulari
    @Override
    public boolean addMovie(Movie movie) {
        try {
            Connection conn = JdbcConnector.getConnection();

            String sql = "INSERT INTO movies (title, description, year) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setLong(3, movie.getYear());

            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Actualitza la informacio de una pelicula segons el que s'introdeix al formulari de la peli
    @Override
    public Movie updateMovie(Movie movie) {
        try {
            Connection conn = JdbcConnector.getConnection();

            String sql = "UPDATE movies SET title = ?, description = ?, year = ? WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setLong(3, movie.getYear());
            ps.setLong(4, movie.getId());

            int rowsAffected = ps.executeUpdate();

            ps.close();
            conn.close();

            if (rowsAffected > 0) {
                return movie;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la película", e);
        }
    }

    //Elimina la pelicula que coincideix amb l'id especificat
    @Override
    public Movie deleteMovie(Long id) {
        Movie movieToDelete = findById(id);
        if (movieToDelete == null) {
            return null;
        }

        String sql = "DELETE FROM movies WHERE id = ?";
        try {
            Connection conn = JdbcConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la película con id " + id, e);
        }

        return movieToDelete;
    }
}