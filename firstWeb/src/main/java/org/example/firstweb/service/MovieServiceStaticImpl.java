package org.example.firstweb.service;

import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;

import java.util.ArrayList;
import java.util.List;
/*
public class MovieServiceStaticImpl implements MovieService {

    private static List<Movie> movieList = new ArrayList<Movie>();

    static {
        movieList.add(new Movie(1L, "The Matrix", "Sci-fi cyberpunk movie.", 1999L));
        movieList.add(new Movie(2L, "Interstellar", "Space exploration.", 2014L));
        movieList.add(new Movie(3L, "Inception", "Dream within a dream.", 2010L));
        movieList.add(new Movie(4L, "The Godfather", "Mafia family drama.", 1972L));
        movieList.add(new Movie(5L, "Pulp Fiction", "Non-linear storytelling.", 1994L));
    }

    @Override
    public List<MovieDto> findAll() {
        return movieList;
    }

    @Override
    public MovieDto findById(Long id) {
        for (Movie movie : movieList) {
            if (movie.getId() ==  id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public boolean addMovie(MovieDto movie) {
        if (movieList.contains(movie)) {
            return false;
        }
        movieList.add(movie);
        return true;
    }

    @Override
    public MovieDto deleteMovie(Long id) {
        return findById(id);
    }
}
*/