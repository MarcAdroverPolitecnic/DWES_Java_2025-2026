package cat.politecnicllevant.prova.configuration;

import cat.politecnicllevant.prova.middleware.JwtAuthenticationFilter;
import cat.politecnicllevant.prova.exception.SecurityExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuració de seguretat per a l'aplicació.
 * Habilita la seguretat a nivell de mètode.
 */
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final SecurityExceptionHandler securityExceptionHandler;

    /**
     * Defineix la cadena de filtres de seguretat.
     * @param http Objecte HttpSecurity per configurar la seguretat web.
     * @return La cadena de filtres de seguretat construïda.
     * @throws Exception Si hi ha un error en la configuració.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Configuració de CORS (Cross-Origin Resource Sharing) amb els valors per defecte.
                .cors(Customizer.withDefaults())
                // Deshabilita la protecció CSRF (Cross-Site Request Forgery) perquè utilitzem JWT.
                .csrf(AbstractHttpConfigurer::disable)
                // Configuració de la gestió de sessions com a STATELESS, ja que no es mantindrà l'estat de la sessió al servidor.
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Gestió d'excepcions de seguretat.
                .exceptionHandling(ex -> ex
                        // Punt d'entrada per a l'autenticació, retorna 401 amb cos JSON.
                        .authenticationEntryPoint(securityExceptionHandler)
                        // Gestor per a l'accés denegat, retorna 403 amb cos JSON.
                        .accessDeniedHandler(securityExceptionHandler)
                )
                // Autorització de les peticions HTTP.
                .authorizeHttpRequests(req -> req
                        // Permet l'accés públic a l'endpoint de login.
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        // Permet l'accés públic a l'endpoint per obtenir les pel·lícules.
                        .requestMatchers(HttpMethod.GET, "/api/movie").permitAll()
                        // Qualsevol altra petició requereix autenticació.
                        .anyRequest().authenticated()
                )
                // Afegeix el filtre d'autenticació JWT abans del filtre d'autenticació
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        // Permet qualsevol origen amb suport per a credencials
//        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//
//        // Mètodes HTTP permesos
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//
//        // Headers permesos
//        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//
//        // Temps en memòria cau de la configuració CORS
//        configuration.setMaxAge(3600L);
//
//        // Habilita l'enviament de credencials (cookies, auth, etc.)
//        configuration.setAllowCredentials(true);
//
//        // Aplica valors per defecte bàsics (opcional)
//        configuration.applyPermitDefaultValues();
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

}
