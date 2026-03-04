package cat.politecnicllevant.prova.controllers;

import cat.politecnicllevant.prova.dto.CreateMovieDto;
import cat.politecnicllevant.prova.dto.MovieDto;
import cat.politecnicllevant.prova.exception.ResourceNotFoundException;
import cat.politecnicllevant.prova.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

//@RestController és com utilitzar @Controller y @ResponseBody al mateix temps.
// Recordau que @ResponseBody serveix per tornar informació en format JSON o XML en les requests.
@RestController
@RequestMapping("/api/movie")
//@Controller
//@ResponseBody
@Validated
@RequiredArgsConstructor
public class MovieController {

    //injecció per constructor gràcies a atribut final i @RequiredArgsConstructor
    public final MovieService movieService;

    //També es podria fer per @Autowired en l'atribut
//    @Autowired
//    public final MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@Min(1) @PathVariable Long id) {
            return ResponseEntity.ok(movieService.getMovieById(id));
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bygenre/{genre}")
    public List<MovieDto> moviesByGenre(@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }

    @PostMapping()
    public ResponseEntity<?> createMovie(@Valid @RequestBody CreateMovieDto movieDto) {
            MovieDto created = movieService.createMovie(movieDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
