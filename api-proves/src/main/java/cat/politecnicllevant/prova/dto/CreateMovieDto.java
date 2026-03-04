package cat.politecnicllevant.prova.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieDto {

    @NotBlank(message = "title is required")
    @Size(max = 10, message = "title must not exceed 10 characters")
    private String title;

    @Size(max = 2000, message = "synopsis must not exceed 2000 characters")
    private String synopsis;

    @NotNull(message = "year is required")
    @Min(value = 1888, message = "year must be >= 1888")
    @Max(value = 2100, message = "year must be <= 2100")
    private Integer year;

    @NotNull(message = "genreId is required")
    @Positive(message = "genreId must be a positive number")
    private Long genreId;
}
