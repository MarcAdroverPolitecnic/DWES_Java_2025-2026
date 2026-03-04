package cat.politecnicllevant.prova.controllers;

import cat.politecnicllevant.prova.domain.Movie;
import cat.politecnicllevant.prova.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController és com utilitzar @Controller y @ResponseBody al mateix temps.
// Recordau que @ResponseBody serveix per tornar informació en format JSON o XML en les requests.
@RestController
//@Controller
//@ResponseBody
@RequiredArgsConstructor
public class MovieController {

    //injecció per constructor gràcies a atribut final i @RequiredArgsConstructor
    public final MovieService movieService;

    //També es podria fer per @Autowired en l'atribut
//    @Autowired
//    public final MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> showAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return movies;
    }

    @GetMapping("/movies/{genre}")
    public List<Movie> moviesByGenre(@PathVariable String genre){
        List<Movie> movies = movieService.getMoviesByGenre(genre);
        return movies;
    }
}
