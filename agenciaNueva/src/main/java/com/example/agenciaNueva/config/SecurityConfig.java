package com.example.agenciaNueva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/hoteles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/hoteles/disponibles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vuelos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/vuelos/disponibles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reserva-hotel/crear").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reserva-vuelo/nuevo").permitAll()

                        .requestMatchers(HttpMethod.POST, "/hoteles/nuevo").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/hoteles/actualizar/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/hoteles/borrar/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/vuelos/nuevo").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/vuelos/actualizar/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/vuelos/borrar/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/personas/nuevo").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/personas/actualizar/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/personas/borrar/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}