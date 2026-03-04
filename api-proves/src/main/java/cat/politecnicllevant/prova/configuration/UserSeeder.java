package cat.politecnicllevant.prova.configuration;

import cat.politecnicllevant.prova.domain.User;
import cat.politecnicllevant.prova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final Faker faker;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("pprohens@politecnicllevant.cat");
            admin.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
            admin.setEnabled(true);
            admin.setRoles(List.of("ROLE_ADMIN", "ROLE_USER"));
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("employee")) {
            User employee = new User();
            employee.setUsername("emplyee");
            employee.setEmail(faker.internet().emailAddress());
            employee.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
            employee.setEnabled(true);
            employee.setRoles(List.of("ROLE_USER"));
            userRepository.save(employee);
        }
    }
}
