package org.example.firstweb.dao;

import jakarta.servlet.ServletException;
import org.example.firstweb.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDao {

    public boolean login(String username, String password);
    public boolean register(User user);
}
