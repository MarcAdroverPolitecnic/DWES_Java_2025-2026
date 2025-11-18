package org.example.firstweb.dao;

import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> findAll();
    Movie findById(Long id);
    boolean addMovie(Movie movie);
    Movie deleteMovie(Long id);
    Movie updateMovie(Movie movie);
}
