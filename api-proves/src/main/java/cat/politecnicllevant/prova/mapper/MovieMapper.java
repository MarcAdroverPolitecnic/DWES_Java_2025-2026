package cat.politecnicllevant.prova.mapper;

import cat.politecnicllevant.prova.domain.Movie;
import cat.politecnicllevant.prova.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(source = "genre.id", target = "genreId")
    @Mapping(source = "genre.name", target = "genreName")
    MovieDto toDto(Movie movie);

    List<MovieDto> toDtoList(List<Movie> movies);

    @Mapping(source = "genreId", target = "genre.id")
    @Mapping(target = "genre.name", ignore = true)
    @Mapping(target = "genre.description", ignore = true)
    Movie toEntity(MovieDto dto);
}
