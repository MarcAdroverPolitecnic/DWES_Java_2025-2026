package org.example.firstweb.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private Long year;
    private List<CommentDto> comments = new ArrayList<>();
}