package cat.politecnicllevant.prova.service;

import cat.politecnicllevant.prova.domain.User;
import cat.politecnicllevant.prova.exception.UserNotFoundException;
import cat.politecnicllevant.prova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String login(String userName, String password) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("The user " + userName + " does not exist"));

        // Verificam el password
        if (!passwordMatches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong credentials for user " + userName);
        }

        // Genera i retorna token
        return jwtService.generateToken(user);
    }

    public boolean passwordMatches(String rawPassword, String encryptedPassword) {
        // Usa una biblioteca como BCrypt para comparar contraseñas
        return BCrypt.checkpw(rawPassword, encryptedPassword);
    }
}
