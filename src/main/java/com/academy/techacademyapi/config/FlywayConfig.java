package com.academy.techacademyapi.config;


import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    
    @Bean
    @Profile("dev") // Only run in dev profile
    public CommandLineRunner flywayMigrate(DataSource dataSource) {
        return args -> {
            // Clean the database first (will drop all tables)
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .cleanDisabled(false) // Allow cleaning
                    .load();
            
            // Clean the database
            flyway.clean();
            
            // Then migrate
            flyway.migrate();
        };
    }
} 