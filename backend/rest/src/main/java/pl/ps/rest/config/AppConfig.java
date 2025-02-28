package pl.ps.rest.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Piotr Skowron
 */
@Configuration
@ComponentScan(basePackages  = "pl.ps")
@EntityScan(basePackages = "pl.ps.model.entity")
@EnableJpaRepositories(basePackages = "pl.ps.model.repository")
@SpringBootConfiguration
@EnableAutoConfiguration
public class AppConfig {
}
