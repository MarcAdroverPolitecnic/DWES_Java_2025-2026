package org.example.firstweb.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.firstweb.model.Movie;
import org.example.firstweb.service.MovieService;
import org.example.firstweb.service.MovieServiceJDBCImpl;
import org.example.firstweb.util.Mapper;
//import org.example.firstweb.service.MovieServiceStaticImpl;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "movieServlet", value = "/movies")
public class MovieServlet extends HttpServlet {

    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        movieService = new MovieServiceJDBCImpl();
    }

      /*
    * Mètode que antèn les peticions Get.
    * -Si no reb paràmatre, mostra totes les pelis.
    * -Si reb paràmetre, mostra la peli específica.
      */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if(session != null){
        session.setAttribute("username", session.getAttribute("username"));}

        if (req.getParameter("id") == null){
            req.setAttribute("movies", movieService.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req,resp);
        } else {
            Long id = Long.valueOf(req.getParameter("id"));

            if (id >= 0 && id < movieService.findAll().size()) {
                req.setAttribute("movie", movieService.findById(id));
                req.getRequestDispatcher("movie.jsp").forward(req,resp);
            }
        }
    }

      /*
    * Antèn les peticions Post.
    * Si reb a través de _method PUT o DELETE, redirigeix a mètodes corresponents.
    * Si no reb cap paràmetre, crea una nova pelicula.
      */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{

            String method = req.getParameter("_method");

            if ("DELETE".equalsIgnoreCase(method)) {
                doDelete(req, resp);
                return;
            }

            if ("PUT".equalsIgnoreCase(method)) {
                doPut(req, resp);
                return;
            }

            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String year = req.getParameter("year");

            Movie movie = new Movie(title, description, Long.valueOf(year));

            movieService.addMovie(Mapper.toDto(movie));

            req.setAttribute("movies", movieService.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req,resp);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

      /*
    * Atèn les petidions Delete.
    * Elimina la pelicula que coincideix amb la id especificada.
      */


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parametroId = req.getParameter("id");

        try{
            Long id = Long.parseLong(parametroId);
            movieService.deleteMovie(id);

            req.setAttribute("movies", movieService.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req,resp);
        }
        catch(Exception e){
            resp.getWriter().println(e.getMessage());
        }
    }

      /*
    * Aten les peticions Put.
    * Actualitza una pelicula existent
      */

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String year = req.getParameter("year");

            Movie movie = new Movie(id, title, description, Long.valueOf(year));
            movieService.updateMovie(Mapper.toDto(movie));

            req.setAttribute("movies", movieService.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println(e.getMessage());
        }
    }



}

