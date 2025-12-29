package org.example.prova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class SampleController {

    //Aquesta part de codi basicament fa que cuan es va a / es crida a resources/templates/moviesList.html

    //Aqui per mostrar la pagina de index, hauriem de posar localhost:8080/mostra?nom=Pere&edat=22


    @GetMapping
    public String showAllMovies(){
        return "moviesList";
    }

    @GetMapping("/{id}")
    public String showMovie(@PathVariable String id, Model model){
        return "movie";
    }
}
