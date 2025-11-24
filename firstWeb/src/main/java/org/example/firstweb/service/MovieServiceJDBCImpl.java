package org.example.firstweb.service;

import org.example.firstweb.dao.MovieDao;
import org.example.firstweb.dao.MovieJDBCDao;
import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.JdbcConnector;
import org.example.firstweb.util.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieServiceJDBCImpl implements MovieService {
    public static Connection connection;

    MovieDao dao = new MovieJDBCDao();
    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = dao.findAll();
        return Mapper.toDtoList(movies);
    }

    @Override
    public MovieDto findById(Long id) {
        Movie movie = dao.findById(id);
        return Mapper.toDto(movie);
    }

    @Override
    public boolean addMovie(MovieDto moviedto) {
        return dao.addMovie(Mapper.toEntity(moviedto));
    }

    @Override
    public MovieDto deleteMovie(Long id) {
        Movie movie = dao.deleteMovie(id);
        return Mapper.toDto(movie);
    }


    @Override
    public MovieDto updateMovie(MovieDto moviedto) {
        Movie movieToUpdate = Mapper.toEntity(moviedto);
        Movie updatedMovie = dao.updateMovie(movieToUpdate);

        return Mapper.toDto(updatedMovie);
    }


    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
