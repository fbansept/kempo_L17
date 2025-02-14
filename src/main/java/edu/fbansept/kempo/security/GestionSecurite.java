package edu.fbansept.kempo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class GestionSecurite {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDetailsService appUserDetailsService;

    @Autowired
    private OncePerRequestFilter jwtFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain httpSecurity (HttpSecurity http) throws Exception {
        return http
                .csrf(config -> config.disable())
                .cors(config -> config.configurationSource(corsConfigurationSource()))
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));
        corsConfiguration.setAllowedHeaders(List.of(
                "Authorization", // Utilisé pour envoyer un JWT
                "Content-Type", // Envoie vers serveur d'un format particulier (ex: Content-Type: application/json)
                "Accept", //Réception depuis le serveur d'un format particulier (ex: Accept: application/json)
                "Origin", //Permet d'indiquer d'où la requête provient (quelle IP / domaine ...)
                "X-Requested-With" //Permet d'indiquer quel a été l'action a l'origine de la requête, souvent dans le but de préciser que c'est javascript qui l'exécute et non une page (ex : X-Requested-With: XMLHttpRequest)
        ));
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        // Permettre au client d'accéder au token (ex:JWT) si il envoyé en réponse
        //(ex: pour un rafraîchissement)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //Attribue la configuration à l'ensemble des requetes
        //(ex : il serait possible de ne l'appliquer qu'au requetes commençant par "/api" avec: "/api/**")
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }



}
