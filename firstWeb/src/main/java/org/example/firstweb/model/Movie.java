package org.example.firstweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private Long year;
    @Transient private float rating;

    //FetchType.Eager (Sempre te mostra sa coleccio) i si ho canviam per Lazy
    //no te mostra fins que se demani
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();


    public Movie(String title, String description, Long year) {
        this.title = title;
        this.description = description;
        this.year = year;
    }

    public Movie(Long movieId, String title, String description, Long aLong) {
        this.id = movieId;
        this.title = title;
        this.description = description;
        this.year = aLong;
    }
}
