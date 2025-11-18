package org.example.firstweb.service;

import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    MovieDto findById(Long id);
    boolean addMovie(Movie movie);
    MovieDto updateMovie(Movie movie);
    MovieDto deleteMovie(Long id);
}