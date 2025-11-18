package cat.politecnicllevant.exemples.generic;

import cat.politecnicllevant.exemples.generic.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Deck<T extends Card> {

    private List<T> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(T card){
        if (cards.contains(card))
            throw new IllegalArgumentException();
        cards.add(card);
    }

    public T drawCard(){
        if (cards.isEmpty())
            return null;
        return cards.removeLast();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void sort(){
        cards = cards.stream().sorted().collect(Collectors.toList());
    }

    public List<T> getCheapCards() {
        Predicate<T> cheaperThan10 = c -> c.getValue() < 10;
        Predicate<T> isFireball = c-> c.getName().equals("Fireball");

        return cards.stream().filter(cheaperThan10.and(isFireball)).toList();
    }

    public List<T> getCards() {
        return new ArrayList<>(cards);
    }


    // sort


}
