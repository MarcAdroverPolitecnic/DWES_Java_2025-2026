package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Deck<T extends Card> {

    List<T> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(T card) {
        if(cards.contains(card)){
            throw new IllegalArgumentException("Card existente");
        }
        cards.add(card);
    }


    public T drawCard(){
        if(cards.isEmpty()){
            return null;
        }
        return cards.removeLast();
    }

    public List<T> getCards(){
        return new ArrayList<>(cards);
    }


    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void sort(){
        cards = cards.stream().sorted().collect(Collectors.toList());
    }

    public List<T> getCheapCards(){
        Predicate<T> cheaperThan10 = ( c -> c.getValue() > 10);
        Predicate<T> isInstant = c -> c.getName().equals("Fireball");

        return cards.stream().filter(cheaperThan10.and(isInstant)).toList();
    }

    public List<String> cardUpperNames(){
        return cards.stream().map(c -> c.getName().toUpperCase()).toList();
    }

    public List<T> funnyCards(){
        return cards.stream().map(c -> {
            c.setName(c.getName().toUpperCase());
            c.setValue(c.getValue() * 10);
            return c;
        }).toList();
    }

}

