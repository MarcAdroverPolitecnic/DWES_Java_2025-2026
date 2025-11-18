package org.example.firstweb.util;

import org.example.firstweb.dto.CommentDto;
import org.example.firstweb.dto.MovieDto;
import org.example.firstweb.model.Comment;
import org.example.firstweb.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    private Mapper() {
    }

    public static MovieDto toDto(Movie movie) {
        if (movie == null) {
            return null;
        }
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setYear(movie.getYear());

        if (movie.getComments() != null) {
            dto.setComments(
                    movie.getComments().stream()
                            .map(Mapper::commentToDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Movie toEntity(MovieDto dto) {
        if (dto == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setYear(dto.getYear());
        return movie;
    }

    public static CommentDto commentToDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDto dto = new CommentDto();
        dto.setCommentText(comment.getCommentText());
        return dto;
    }

    public static List<MovieDto> toDtoList(List<Movie> movies) {
        return movies.stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Movie> toEntityList(List<MovieDto> dtos) {
        return dtos.stream()
                .map(Mapper::toEntity)
                .collect(Collectors.toList());
    }
}