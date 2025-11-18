package org.example.firstweb.controller;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.firstweb.dao.UserOrm;
import org.example.firstweb.model.User;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserOrm orm = new UserOrm();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = orm.findUserByUsername(username);

        if (user != null) {

            if (user.getPassword().equals(password)) {

                createSessionAndRedirect(request, response, user.getUsername());

            } else {
                request.setAttribute("error", "La contraseña es incorrecta.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {

            User newUser = new User(username, password);

            try {
                orm.addUser(newUser);

                createSessionAndRedirect(request, response, newUser.getUsername());

            } catch (Exception e) {
                request.setAttribute("error", "Error al crear el nuevo usuario.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    private void createSessionAndRedirect(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setMaxInactiveInterval(30 * 60);
        response.sendRedirect("movies");
    }
}