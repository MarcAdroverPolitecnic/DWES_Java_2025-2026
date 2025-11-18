package org.example;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Card {

    private int value;
    private String name;
    private boolean faceDown;


}


