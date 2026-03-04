package cat.politecnicllevant.prova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String synopsis;
    private int year;
    private Long genreId;
    private String genreName;
}
