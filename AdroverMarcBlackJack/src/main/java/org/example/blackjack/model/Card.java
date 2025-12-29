package org.example.blackjack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String image;
    private String value;
    private String suit;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public Card(String code, String image, String value, String suit, Deck deck) {
        this.code = code;
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.deck = deck;
    }
}