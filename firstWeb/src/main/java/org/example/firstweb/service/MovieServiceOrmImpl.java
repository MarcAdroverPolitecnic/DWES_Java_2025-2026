package org.example.firstweb.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.firstweb.dao.MovieDao;
import org.example.firstweb.dao.MovieOrmDao;
import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Movie;
import org.example.firstweb.util.ConnectionManager;
import org.example.firstweb.util.Mapper;

import java.sql.SQLException;
import java.util.List;

public class MovieServiceOrmImpl implements MovieService {

    MovieDao dao = new MovieOrmDao();

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = dao.findAll();

        return Mapper.toDtoList(movies);
    }

    @Override
    public MovieDto findById(Long id) {
        Movie movieDto = dao.findById(id);

        return Mapper.toDto(movieDto);
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
}
