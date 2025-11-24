package org.example.firstweb.service;

import jakarta.servlet.ServletException;
import org.example.firstweb.dao.UserDaoJdbc;
import org.example.firstweb.model.User;

import java.io.IOException;

public class UserService {
    UserDaoJdbc userDao = new UserDaoJdbc();

    public boolean login(String username, String password) throws ServletException, IOException {
        return userDao.login(username, password);
    }

    public boolean register(User user) throws ServletException, IOException {
        return userDao.register(user);
    }
}
