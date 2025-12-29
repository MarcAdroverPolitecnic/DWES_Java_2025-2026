package org.example.examen.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
*/
    @Column(name = "correct_answers")
    private int correctAnswers;

    @Column(name = "incorrect_answers")
    private int incorrectAnswers;

    private long duration;

}
