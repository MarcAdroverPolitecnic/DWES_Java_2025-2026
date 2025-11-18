package org.example.model;

import lombok.Data;

@Data
public abstract class GameOfThronesEntry implements Comparable<GameOfThronesEntry> {
    private int id;


    public abstract int compareTo(GameOfThronesEntry other);

    public abstract void printGameOfThronesEntry();

    public abstract void setNameUpperCase();

}
