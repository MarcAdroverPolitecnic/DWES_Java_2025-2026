package org.example.examen.dto;

import org.example.examen.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    //private UserDto user;
    private int correctAnswers;
    private int incorrectAnswers;
    private long duration;

    public static GameDto fromEntity(Game game) {
        if (game == null) {
            return null;
        }
        return GameDto.builder()
                .id(game.getId())
                //.user(UserDto.fromEntity(game.getUser()))
                .correctAnswers(game.getCorrectAnswers())
                .incorrectAnswers(game.getIncorrectAnswers())
                .duration(game.getDuration())
                .build();
    }
}