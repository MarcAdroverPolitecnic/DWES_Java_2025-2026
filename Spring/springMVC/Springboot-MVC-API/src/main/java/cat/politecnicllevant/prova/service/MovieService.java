package cat.politecnicllevant.prova.service;

import cat.politecnicllevant.prova.domain.Movie;
import cat.politecnicllevant.prova.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class MovieService {

    //aquí injectem per @Autowired, perquè vegeu diferents exemples
    @Autowired
    private MovieRepository movieRepository;

//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElseThrow();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findMoviesByGenreName(genre);
    }

}
