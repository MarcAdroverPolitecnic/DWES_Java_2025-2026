package org.example.firstweb.util;

import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.Movie;

public class MovieMapper {
    public static ShowMovieDto movieToShow(Movie movie) {
        ShowMovieDto dto = new ShowMovieDto();

        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setYear(movie.getYear());
        //dto.setRate(movie.getRate());
        //dto.setComments(movie.getComments());
        return dto;
    }
    public static Movie ShowToMovie(ShowMovieDto showMovieDto) {
        Movie mv = new Movie();

        mv.setId(showMovieDto.getId());
        mv.setTitle(showMovieDto.getTitle());
        mv.setDescription(showMovieDto.getDescription());
        mv.setYear(showMovieDto.getYear());
        //mv.setRate(showMovieDto.getRate());
        //mv.setComments(showMovieDto.getComments());
        return mv;
    }
}
