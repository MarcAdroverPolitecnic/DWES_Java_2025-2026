package org.example.firstweb.dao;

import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;

import java.util.ArrayList;
import java.util.List;

public interface MovieDao {
    public static List<Movie> movies = new ArrayList<Movie>();
    public List<Movie> findAll();
    public Movie findById(Long id);
    public boolean addMovie(Movie movie);
    public Movie deleteMovieById(Long id);
    public boolean updateMovie(Movie movie);
    public List<Comment> findCommentsByMovieId(Long id);
}
