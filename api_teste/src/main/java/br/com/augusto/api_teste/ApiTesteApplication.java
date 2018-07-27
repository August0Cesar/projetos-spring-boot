package br.com.augusto.api_teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTesteApplication.class, args);
	}
}
