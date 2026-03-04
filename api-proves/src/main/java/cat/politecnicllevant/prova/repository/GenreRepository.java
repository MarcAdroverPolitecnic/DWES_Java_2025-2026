package cat.politecnicllevant.prova.repository;

import cat.politecnicllevant.prova.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
