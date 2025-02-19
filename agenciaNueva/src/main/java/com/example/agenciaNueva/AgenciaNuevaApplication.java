package com.example.agenciaNueva;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgenciaNuevaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgenciaNuevaApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API de Nueva Agencia")
				.version("1.0")
				.description("API para la gestion de reservas de hoteles y vuelos."));
	}
}
