package cat.politecnicllevant.prova.configuration;

import com.paypal.base.rest.APIContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public APIContext apiContext() {
        return new APIContext(
                "CLIENT_ID",
                "CLIENT_SECRET",
                "sandbox"
        );
    }
}