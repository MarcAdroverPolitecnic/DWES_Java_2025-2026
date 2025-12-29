/*


--------------------MODEL-------------------------------
* public class Card {
    private int id; // ID autogenerado en BD
    private String code; // Ej: "KS"
    private String image; // URL
    private String value; // Ej: "KING"
    private String suit; // Ej: "SPADES"
    private String deckId; // Foreign key al Deck

    public Card() {}

    public Card(String code, String image, String value, String suit, String deckId) {
        this.code = code;
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.deckId = deckId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getSuit() { return suit; }
    public void setSuit(String suit) { this.suit = suit; }
    public String getDeckId() { return deckId; }
    public void setDeckId(String deckId) { this.deckId = deckId; }
}
*
*
*
*
* import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String id; // El ID que nos da la API (ej: 3p40paa87x90)
    private int remaining; // Cartas restantes
    private boolean shuffled;
    private List<Card> drawnCards; // Lista de cartas robadas asociadas

    public Deck() {
        this.drawnCards = new ArrayList<>();
    }

    public Deck(String id, int remaining, boolean shuffled) {
        this.id = id;
        this.remaining = remaining;
        this.shuffled = shuffled;
        this.drawnCards = new ArrayList<>();
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getRemaining() { return remaining; }
    public void setRemaining(int remaining) { this.remaining = remaining; }
    public boolean isShuffled() { return shuffled; }
    public void setShuffled(boolean shuffled) { this.shuffled = shuffled; }
    public List<Card> getDrawnCards() { return drawnCards; }
    public void setDrawnCards(List<Card> drawnCards) { this.drawnCards = drawnCards; }

    // Método helper para añadir una carta
    public void addCard(Card card) {
        this.drawnCards.add(card);
    }
}
*--------------------------------------------DTO------------------------------------------------------------------
*
*
* import java.util.List;

// Clase interna o auxiliar para mapear el JSON exacto de la API
public class DeckApiResponse {
    public boolean success;
    public String deck_id;
    public int remaining;
    public boolean shuffled;
    public List<CardApiObj> cards; // Solo viene relleno al hacer /draw/

    // Subclase para mapear el objeto carta dentro del JSON
    public static class CardApiObj {
        public String code;
        public String image;
        public String value;
        public String suit;
    }
}
*
*------------------------------------------------Provider-------------------------------------------------------------
*
* import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeckProvider {

    private static final String BASE_URL = "https://www.deckofcardsapi.com/api/deck/";
    private final Gson gson = new Gson();

    // 1. Pedir nuevo Deck mezclado
    public DeckApiResponse createNewDeck(int deckCount) throws Exception {
        String urlString = BASE_URL + "new/shuffle/?deck_count=" + deckCount;
        String jsonResponse = sendGetRequest(urlString);
        return gson.fromJson(jsonResponse, DeckApiResponse.class);
    }

    // 2. Robar cartas de un deck existente
    public DeckApiResponse drawCards(String deckId, int count) throws Exception {
        String urlString = BASE_URL + deckId + "/draw/?count=" + count;
        String jsonResponse = sendGetRequest(urlString);
        return gson.fromJson(jsonResponse, DeckApiResponse.class);
    }

    // Helper para la petición HTTP
    private String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Timeout para seguridad
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        }
    }
}
*
*
*
*-------------------------------------------Dao--------------------------------------------------------------
*
* import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeckDao {

    // GUARDAR O ACTUALIZAR DECK
    // Usamos MERGE o un INSERT ON DUPLICATE KEY UPDATE (dependiendo de tu SQL, aquí pongo lógica genérica)
    public void saveOrUpdateDeck(Deck deck) throws SQLException {
        String sql = "INSERT INTO decks (id, remaining) VALUES (?, ?) " +
                     "ON DUPLICATE KEY UPDATE remaining = ?";
                     // Nota: Ajustar sintaxis según si usas MySQL, Postgres o H2

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, deck.getId());
            ps.setInt(2, deck.getRemaining());
            ps.setInt(3, deck.getRemaining());
            ps.executeUpdate();
        }
    }

    // GUARDAR UNA CARTA
    public void saveCard(Card card) throws SQLException {
        String sql = "INSERT INTO cards (code, image, value, suit, deck_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, card.getCode());
            ps.setString(2, card.getImage());
            ps.setString(3, card.getValue());
            ps.setString(4, card.getSuit());
            ps.setString(5, card.getDeckId());
            ps.executeUpdate();
        }
    }

    // OBTENER DECK COMPLETO CON SUS CARTAS (JOIN)
    public Deck getDeckById(String deckId) throws SQLException {
        Deck deck = null;
        String sqlDeck = "SELECT * FROM decks WHERE id = ?";
        String sqlCards = "SELECT * FROM cards WHERE deck_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Cargar info del Deck
            try (PreparedStatement ps = conn.prepareStatement(sqlDeck)) {
                ps.setString(1, deckId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    deck = new Deck();
                    deck.setId(rs.getString("id"));
                    deck.setRemaining(rs.getInt("remaining"));
                }
            }

            // 2. Si el deck existe, cargar sus cartas robadas
            if (deck != null) {
                try (PreparedStatement ps = conn.prepareStatement(sqlCards)) {
                    ps.setString(1, deckId);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Card c = new Card();
                        c.setId(rs.getInt("id"));
                        c.setCode(rs.getString("code"));
                        c.setImage(rs.getString("image"));
                        c.setValue(rs.getString("value"));
                        c.setSuit(rs.getString("suit"));
                        c.setDeckId(deckId);
                        deck.addCard(c);
                    }
                }
            }
        }
        return deck;
    }
}
*
*----------------------------------------Service-------------------------------------------------------------
*
*
*
*
* public class GameManager {

    private DeckProvider apiProvider = new DeckProvider();
    private DeckDao deckDao = new DeckDao();

    // CASO DE USO 1: Nuevo Juego
    public Deck startNewGame(int deckCount) throws Exception {
        // 1. Llamar a API externa
        DeckApiResponse response = apiProvider.createNewDeck(deckCount);

        // 2. Mapear a nuestra entidad
        Deck newDeck = new Deck(response.deck_id, response.remaining, response.shuffled);

        // 3. Guardar en BD
        deckDao.saveOrUpdateDeck(newDeck);

        // 4. Retornar (se podría hacer un getDeckById para asegurar que viene de BD)
        return newDeck;
    }

    // CASO DE USO 2: Robar Carta
    public Deck drawCards(String deckId, int count) throws Exception {
        // 1. Llamar a API externa
        DeckApiResponse response = apiProvider.drawCards(deckId, count);

        // 2. Actualizar el Deck (cartas restantes)
        Deck currentDeck = new Deck();
        currentDeck.setId(response.deck_id);
        currentDeck.setRemaining(response.remaining);
        deckDao.saveOrUpdateDeck(currentDeck); // Actualiza el contador

        // 3. Guardar las cartas nuevas en BD
        for (DeckApiResponse.CardApiObj apiCard : response.cards) {
            Card card = new Card(apiCard.code, apiCard.image, apiCard.value, apiCard.suit, deckId);
            deckDao.saveCard(card);
        }

        // 4. IMPORTANTE: Recuperar el estado completo desde BD para la vista
        return deckDao.getDeckById(deckId);
    }
}
*
*
*
*
*
*
* public class AuthService {

    private static final String HARDCODED_USER = "admin";
    private static final String HARDCODED_PASS = "1234";

    public boolean login(String username, String password) {
        return HARDCODED_USER.equals(username) && HARDCODED_PASS.equals(password);
    }

    // Podrías usar esto en un Filter de Servlet para comprobar la sesión
    public boolean isAuthenticated(Object sessionUserAttribute) {
        return sessionUserAttribute != null;
    }
}

*


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
    private Long id; // Database specific ID

    private String code;
    private String image;
    private String value;
    private String suit;

    // BONUS: Boolean to track if card is face up or down
    private boolean faceUp = true;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    public Card(String code, String image, String value, String suit, Deck deck) {
        this.code = code;
        this.image = image;
        this.value = value;
        this.suit = suit;
        this.deck = deck;
        this.faceUp = true;
    }
}



package org.example.blackjack.service;

import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.blackjack.dao.ConnectionManager;
import org.example.blackjack.model.Card;
import org.example.blackjack.model.Deck;
import org.example.blackjack.util.ApiProvider;

import java.util.List;

public class DeckService implements IDeckService {

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
            // 1. Call API
            String url = "https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=" + numberOfDecks;
            String jsonResponse = ApiProvider.requestApi(url);
            JsonObject json = ApiProvider.parseToJson(jsonResponse);

            // 2. Parse Info
            String deckId = json.get("deck_id").getAsString();
            int remaining = json.get("remaining").getAsInt();

            // 3. Save to DB
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
            // 1. Call API
            String url = "https://www.deckofcardsapi.com/api/deck/" + deckId + "/draw/?count=" + count;
            String jsonResponse = ApiProvider.requestApi(url);
            JsonObject json = ApiProvider.parseToJson(jsonResponse);

            tx.begin();
            Deck deck = em.find(Deck.class, deckId);

            // 2. Update Deck Remaining count
            int remaining = json.get("remaining").getAsInt();
            deck.setRemaining(remaining);

            // 3. Process and Save Cards
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



package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");

        // Mock Auth as per requirements
        if ("1234".equals(p)) {
            req.getSession().setAttribute("user", u);
            resp.sendRedirect(req.getContextPath() + "/decks");
        } else {
            req.setAttribute("error", "Invalid password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}


package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.blackjack.model.Deck;
import org.example.blackjack.service.DeckService;
import java.io.IOException;
import java.util.List;

@WebServlet("/decks")
public class DecksServlet extends HttpServlet {
    private DeckService service = new DeckService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            // "Play deck" - Show specific deck
            Deck deck = service.getDeck(id);
            req.setAttribute("deck", deck);
            req.getRequestDispatcher("/deck.jsp").forward(req, resp);
        } else {
            // "Home" - Show list
            List<Deck> decks = service.getAllDecks();
            req.setAttribute("decks", decks);
            req.getRequestDispatcher("/decks.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Protect: Only logged users can create
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Create new deck
        String numStr = req.getParameter("numberOfDecks");
        int num = (numStr == null) ? 1 : Integer.parseInt(numStr);

        service.shuffleNewDeck(num);

        // Redirect to list to avoid form resubmission
        resp.sendRedirect(req.getContextPath() + "/decks");
    }
}


package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.blackjack.service.DeckService;
import java.io.IOException;

@WebServlet("/draw")
public class DrawServlet extends HttpServlet {
    private DeckService service = new DeckService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Protect: Only logged users can draw
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String deckId = req.getParameter("deckId");
        int count = Integer.parseInt(req.getParameter("numberOfCards"));

        // Logic: Call API, Update DB
        service.drawCards(deckId, count);

        // Redirect back to the specific deck view
        resp.sendRedirect(req.getContextPath() + "/decks?id=" + deckId);
    }
}



















*
* */