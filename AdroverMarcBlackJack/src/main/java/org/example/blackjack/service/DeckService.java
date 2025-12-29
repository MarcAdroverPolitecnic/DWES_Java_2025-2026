package org.example.blackjack.service;

import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.blackjack.dao.ConnectionManager;
import org.example.blackjack.model.Card;
import org.example.blackjack.model.Deck;
import org.example.blackjack.util.ApiProvider;

import java.util.List;

public class DeckService implements IDeckService{
    @Override
    public List<Deck> getAllDecks() {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Deck d", Deck.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Deck getDeck(String id) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            return em.find(Deck.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Deck shuffleNewDeck(int numberOfDecks) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Deck deck = null;

        try {
            String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=" + numberOfDecks;
            String jsonResponse = ApiProvider.requestApi(url);
            JsonObject json = ApiProvider.parseToJson(jsonResponse);

            String deckId = json.get("deck_id").getAsString();
            int remaining = json.get("remaining").getAsInt();

            tx.begin();
            deck = new Deck(deckId, remaining);
            em.persist(deck);
            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return deck;
    }

    public void drawCards(String deckId, int count) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            String url = "https://www.deckofcardsapi.com/api/deck/" + deckId + "/draw/?count=" + count;
            String jsonResponse = ApiProvider.requestApi(url);
            JsonObject json = ApiProvider.parseToJson(jsonResponse);

            tx.begin();
            Deck deck = em.find(Deck.class, deckId);

            int remaining = json.get("remaining").getAsInt();
            deck.setRemaining(remaining);

            List<JsonObject> cardObjects = ApiProvider.getListJsonObjects(json, "cards");

            for (JsonObject cardJson : cardObjects) {
                Card card = new Card(
                        cardJson.get("code").getAsString(),
                        cardJson.get("image").getAsString(),
                        cardJson.get("value").getAsString(),
                        cardJson.get("suit").getAsString(),
                        deck
                );
                em.persist(card);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

