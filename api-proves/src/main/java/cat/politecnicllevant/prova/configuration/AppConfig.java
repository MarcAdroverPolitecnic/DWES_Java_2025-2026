package cat.politecnicllevant.prova.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Faker faker(){
        return new Faker();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}