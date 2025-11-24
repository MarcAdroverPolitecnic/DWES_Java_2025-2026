package org.example.firstweb.service;

import org.example.firstweb.dao.MovieDao;
import org.example.firstweb.dao.MovieDaoJdbc;
import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.MovieMapper;

import java.util.ArrayList;
import java.util.List;

public class MovieServicempl {
    MovieDao movieDao = new MovieDaoJdbc();

    public List<ShowMovieDto> findAll() {
        List<Movie> movies = movieDao.findAll();
        List<ShowMovieDto> showMovies =  new ArrayList<>();
        for (Movie movie : movies) {
            showMovies.add(MovieMapper.movieToShow(movie));
        }
        return showMovies;
    }

    public ShowMovieDto findById(Long id) {
        return MovieMapper.movieToShow(movieDao.findById(id));
    }

    public boolean addMovie(ShowMovieDto showMovieDto) {
        return movieDao.addMovie(MovieMapper.ShowToMovie(showMovieDto));
    }

    public ShowMovieDto deleteMovieById(Long id) {
        return MovieMapper.movieToShow(movieDao.deleteMovieById(id));
    }

    public boolean updateMovie(ShowMovieDto showMovieDto){
        return movieDao.updateMovie(MovieMapper.ShowToMovie(showMovieDto));
    }

    public List<Comment> findCommentsByMovieId(Long id){
        return movieDao.findCommentsByMovieId(id);
    }

}