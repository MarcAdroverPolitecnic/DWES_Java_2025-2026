package cat.politecnicllevant.exemples.generic;

import cat.politecnicllevant.exemples.generic.model.Audio;
import cat.politecnicllevant.exemples.generic.model.Image;
import cat.politecnicllevant.exemples.generic.model.MagicCard;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
class Box<T>{
    private T objectA;

    public Box(T objectA) {
        this.objectA = objectA;
    }

    public T getObjectA() {
        return objectA;
    }
}

public class    GenericBasicExample {

    public static void main(String[] args) {

        Deck<MagicCard> magicDeck = new Deck<>();
        magicDeck.addCard(new MagicCard("Elfito", 12, false, "Do elfic things", "Sings a song", "Creature"));
        magicDeck.addCard(new MagicCard("Fireball", 5, false, "Deals damage", "Unknown", "Instant"));
        magicDeck.addCard(new MagicCard("Healing", 25, false, "Restores health", "Unknown", "Spell"));
        magicDeck.addCard(new MagicCard("Raro", 10, false, "Restores health", "Destroys Planet", "Spell"));
        magicDeck.addCard(new MagicCard("Solar Heat", 10, false, "Restores health", "Destroys Planet", "Spell"));
        magicDeck.addCard(new MagicCard("ASDF", 10, false, "Restores health", "Destroys Planet", "Spell"));
        magicDeck.addCard(new MagicCard("Black Lotus", 15, false, "Restores health", "Unknown", "Artifact"));

        System.out.println("\nDeck before shuffle:");
        magicDeck.getCards().forEach(System.out::println);

        System.out.println("\nDraw one card:");
        MagicCard drawn = magicDeck.drawCard();
        System.out.println(drawn);

        System.out.println("\nDeck after drawing one card:");
        magicDeck.getCards().forEach(System.out::println);

        System.out.println("\nShuffle deck:");
        magicDeck.shuffle();
        magicDeck.getCards().forEach(System.out::println);

        System.out.println("\nSort deck:");
        magicDeck.sort();
        magicDeck.getCards().forEach(System.out::println);

        System.out.println("\nAfter add card:");
        magicDeck.addCard(new MagicCard("Candemor", 1, false, "jarl", "-----", "Encantamiento"));
        magicDeck.getCards().forEach(System.out::println);

        System.out.println("\nFilter");
        magicDeck.getCheapCards().forEach(System.out::println);
    }
}
