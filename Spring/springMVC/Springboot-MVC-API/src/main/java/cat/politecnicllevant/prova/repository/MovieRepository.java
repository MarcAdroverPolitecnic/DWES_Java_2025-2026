package cat.politecnicllevant.prova.repository;

import cat.politecnicllevant.prova.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Extenem d'un Repositoy de JPA, un d'ells és el JpaRepository que és dels més complets
public interface MovieRepository extends JpaRepository<Movie,Long> {
    // Podem afegir els nostres mètodes que implementaran les query depenent del nom del mètode.
    // Mireu documentació de SpringData per això.
    List<Movie> findMoviesByGenreName(String genreName);
}
