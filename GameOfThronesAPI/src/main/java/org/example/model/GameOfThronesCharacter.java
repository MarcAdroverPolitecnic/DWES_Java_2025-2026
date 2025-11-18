package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class GameOfThronesCharacter extends GameOfThronesEntry{
        private String firstName;
        private String lastName;
        private String fullName;
        private String title;
        private String family;
        private String image;
        private String imageUrl;

    public GameOfThronesCharacter(String firstName, String lastName, String fullName, String title, String family, String image, String imageUrl) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.title = title;
        this.family = family;
        this.image = image;
        this.imageUrl = imageUrl;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameOfThronesCharacter other = (GameOfThronesCharacter) obj;

        return Objects.equals(this.getId(), other.getId())
                && Objects.equals(this.getFullName(), other.getFullName());
    }

    @Override
    public int compareTo(GameOfThronesEntry other) {
        GameOfThronesCharacter c = (GameOfThronesCharacter) other;

        int result = this.family.compareToIgnoreCase(c.family);
        if (result == 0) {
            result = this.firstName.compareToIgnoreCase(c.firstName);
        }
        return result;
    }

    public void setNameUpperCase(){
        firstName.toUpperCase();
        lastName.toUpperCase();
        fullName.toUpperCase();
    }

    @Override
    public void printGameOfThronesEntry() {
        System.out.println("Id:" + getId());
        System.out.println("FirstName:" + getFirstName() );
        System.out.println("LastName:" + getLastName());
        System.out.println("FullName:" + getFullName());
        System.out.println("Title:" + getTitle());
        System.out.println("Family:" + getFamily());
        System.out.println("Image:" + getImage());
        System.out.println("ImageURL:" + getImageUrl() + "\n");

    }
}
