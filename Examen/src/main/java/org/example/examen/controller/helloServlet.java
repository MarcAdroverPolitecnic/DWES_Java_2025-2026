package org.example.examen.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.examen.service.Service;
import org.example.examen.service.ServiceImpl;

import java.io.IOException;
import java.util.Objects;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class helloServlet extends HttpServlet {

    private Service service;

    @Override
    public void init() {
        service = new ServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/hello-servlet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}