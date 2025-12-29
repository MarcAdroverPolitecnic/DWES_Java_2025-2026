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
            Deck deck = service.getDeck(id);
            req.setAttribute("deck", deck);
            req.getRequestDispatcher("/deck.jsp").forward(req, resp);
        } else {
            List<Deck> decks = service.getAllDecks();
            req.setAttribute("decks", decks);
            req.getRequestDispatcher("/decks.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String numStr = req.getParameter("numberOfDecks");

        int num;
        if(numStr == null){
            num = 1;
        } else{
            num = Integer.parseInt(numStr);
        }

        Deck deck = service.shuffleNewDeck(num);
        String id = deck.getId();

        resp.sendRedirect(req.getContextPath() + "/decks?id=" + id);
    }
}