package org.example.firstweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.firstweb.model.Comment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShowMovieDto {
    private Long id;
    private String title;
    private String description;
    private int year;
    private float rate;
    private List<Comment> comments;

    public ShowMovieDto(Long id, String title, String description, int year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
    }

    public ShowMovieDto(String title, String description, int year) {
        this.title = title;
        this.description = description;
        this.year = year;
    }
}
