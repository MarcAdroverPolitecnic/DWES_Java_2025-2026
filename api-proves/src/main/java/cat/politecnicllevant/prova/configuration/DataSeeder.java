package cat.politecnicllevant.prova.configuration;

import cat.politecnicllevant.prova.domain.Genre;
import cat.politecnicllevant.prova.domain.Movie;
import cat.politecnicllevant.prova.repository.GenreRepository;
import cat.politecnicllevant.prova.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public void run(String... args) {
        if (movieRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker();
        List<Genre> genres = genreRepository.findAll();
        if (genres.isEmpty()) {
            genres = genreRepository.saveAll(defaultGenres(faker));
        }

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Movie movie = new Movie();
            movie.setTitle(faker.movie().name());
            movie.setSynopsis(faker.lorem().sentence(12));
            movie.setYear(faker.number().numberBetween(1980, 2025));
            movie.setGenre(genres.get(faker.number().numberBetween(0, genres.size())));
            movies.add(movie);
        }

        movieRepository.saveAll(movies);
    }

    private List<Genre> defaultGenres(Faker faker) {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(null, "Action", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Adventure", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Comedy", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Drama", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Fantasy", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Horror", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Romance", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Sci-Fi", faker.lorem().sentence(8)));
        genres.add(new Genre(null, "Thriller", faker.lorem().sentence(8)));
        return genres;
    }
}