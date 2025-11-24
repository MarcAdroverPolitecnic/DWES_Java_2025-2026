package org.example.firstweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.firstweb.dto.ShowMovieDto;
import org.example.firstweb.model.User;
import org.example.firstweb.service.MovieServicempl;

import java.io.IOException;

@WebServlet(name = "movieServlet", value = "/movies")
public class MovieServlet extends HttpServlet {
    private final MovieServicempl service = new MovieServicempl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            String method = req.getParameter("_method");
            if (method != null) {
                if ("DELETE".equals(method)) {
                    doDelete(req, resp);
                    return;
                }
                if ("PUT".equals(method)) {
                    doPut(req, resp);
                    return;
                }
            }
        }
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null){
            getAllMovies(req, resp);
        } else {
            Long id = Long.valueOf(req.getParameter("id"));

            if (!(req.getParameter("update") == null)){
                updateMovie(req, resp, id);
            }
            if (id >= 0 && id < service.findAll().size()) {
                getMovie(req, resp, id);
            }
        }
    }

    protected void getAllMovies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        req.setAttribute("username", user.getUsername());
        req.setAttribute("movies", service.findAll());
        req.getRequestDispatcher("movies.jsp").forward(req,resp);
    }

    protected void getMovie(HttpServletRequest req, HttpServletResponse resp, Long id) throws ServletException, IOException {
        req.setAttribute("movie", service.findById(id));
        req.setAttribute("comments", service.findCommentsByMovieId(id));
        req.getRequestDispatcher("movie.jsp").forward(req,resp);
    }

    protected void updateMovie(HttpServletRequest req, HttpServletResponse resp, Long id) throws ServletException, IOException {
        req.setAttribute("movie", service.findById(id));
        req.getRequestDispatcher("movieUpdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int year = Integer.parseInt(req.getParameter("year"));

        ShowMovieDto showMovieDto = new ShowMovieDto(title, description, year);
        service.addMovie(showMovieDto);


        resp.sendRedirect(req.getContextPath() + "/movies");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            service.deleteMovieById(Long.parseLong(id));
        }
        resp.sendRedirect(req.getContextPath() + "/movies");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int year = Integer.parseInt(req.getParameter("year"));

        ShowMovieDto showMovieDto = new ShowMovieDto(id, title, description, year);
        service.updateMovie(showMovieDto);

        resp.sendRedirect(req.getContextPath() + "/movies");
    }
}
