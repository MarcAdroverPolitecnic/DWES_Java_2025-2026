package cat.politecnicllevant.prova.service;

import cat.politecnicllevant.prova.domain.Genre;
import cat.politecnicllevant.prova.domain.Movie;
import cat.politecnicllevant.prova.dto.CreateMovieDto;
import cat.politecnicllevant.prova.dto.MovieDto;
import cat.politecnicllevant.prova.exception.ResourceNotFoundException;
import cat.politecnicllevant.prova.mapper.MovieMapper;
import cat.politecnicllevant.prova.repository.MovieRepository;
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
    @Autowired
    private MovieMapper movieMapper;

//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    public List<MovieDto> getAllMovies(){
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto getMovieById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movieMapper.toDto(movie.orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found")));
    }

    public List<MovieDto> getMoviesByGenre(String genre) {
        return movieMapper.toDtoList(movieRepository.findMoviesByGenreName(genre));
    }

    public MovieDto createMovie(CreateMovieDto createMovieDto) {
        Movie movie = new Movie(
                null,
                createMovieDto.getTitle(),
                createMovieDto.getSynopsis(),
                createMovieDto.getYear(),
                new Genre(createMovieDto.getGenreId(), null, null)
                );
        return movieMapper.toDto(movieRepository.save(movie));
    }

}
