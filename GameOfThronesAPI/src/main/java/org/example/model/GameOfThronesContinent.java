package org.example.model;

import lombok.Data;

import java.util.Objects;

@Data
public class GameOfThronesContinent extends GameOfThronesEntry {
        private String name;

    public GameOfThronesContinent(String name ) {
        super();
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameOfThronesContinent other = (GameOfThronesContinent) obj;

        return Objects.equals(this.getId(), other.getId())
                && Objects.equals(this.getName(), other.getName());
    }

    @Override
    public int compareTo(GameOfThronesEntry other) {
        return this.name.compareToIgnoreCase(((GameOfThronesContinent) other).name);
    }

    @Override
    public void printGameOfThronesEntry() {
        System.out.println("Id:" + getId());
        System.out.println("Name:" + getName());
    }

    public void setNameUpperCase(){
        name.toUpperCase();
    }

}
