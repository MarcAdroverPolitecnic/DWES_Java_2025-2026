
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
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String deckId = req.getParameter("deckId");
        int count = Integer.parseInt(req.getParameter("numberOfCards"));

        service.drawCards(deckId, count);

        resp.sendRedirect(req.getContextPath() + "/decks?id=" + deckId);
    }
}
