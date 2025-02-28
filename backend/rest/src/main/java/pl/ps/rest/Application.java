package pl.ps.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Piotr Skowron
 */
@SpringBootApplication
public class Application implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
