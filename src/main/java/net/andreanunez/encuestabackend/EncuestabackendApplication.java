package net.andreanunez.encuestabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.andreanunez.encuestabackend.security.AppProperties;

@SpringBootApplication
@EnableJpaAuditing // para dejar generar la fecha automáticamente
public class EncuestabackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncuestabackendApplication.class, args);
		System.out.println("FUNCIONANDO");
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	// Bean que sirve para encriptar el password
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Bean global de AppProperties
	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

}
