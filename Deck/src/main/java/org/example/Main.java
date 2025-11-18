package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Card carta1 = new Card(1, "Bastos", "L'as de bastos");
        Card carta2 = new Card(2, "Bastos", "2 de bastos");

        Deck deck = new Deck();
        deck.addCard(carta1);
        deck.addCard(carta2);
        deck.shuffle();

        System.out.println(deck.drawCard());


    }
}