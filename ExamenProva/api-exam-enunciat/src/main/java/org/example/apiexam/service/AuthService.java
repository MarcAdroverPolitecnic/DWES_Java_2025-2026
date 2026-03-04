package org.example.apiexam.service;

import lombok.RequiredArgsConstructor;
import org.example.apiexam.error.BadCredentialsException;
import org.example.apiexam.error.UserNotFoundException;
import org.example.apiexam.model.User;
import org.example.apiexam.repository.UserRepository;
import org.example.apiexam.util.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta para el usuario: " + email);
        }

        return jwtUtils.generateToken(user);
    }
}