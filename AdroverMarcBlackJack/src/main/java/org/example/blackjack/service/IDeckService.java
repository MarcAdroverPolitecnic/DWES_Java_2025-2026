package org.example.blackjack.service;

import org.example.blackjack.model.Card;
import org.example.blackjack.model.Deck;

import java.util.List;

public interface IDeckService {
    List<Deck> getAllDecks();
    Deck getDeck(String id);
    Deck shuffleNewDeck(int numberOfDecks);
}
