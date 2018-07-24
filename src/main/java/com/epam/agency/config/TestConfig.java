package com.epam.agency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Configure application context for development process.
 *
 * @author Hanna_Charnova
 * @version 1.0
 */

@Configuration
@Profile("development")
@ComponentScan(value = {"com.epam.agency.service", "com.epam.agency.repository", "com.epam.agency.config"})
public class TestConfig {

    /**
     * Initialize dataSource to embedded database
     *
     * @return initialized dataSource object
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:init-test-data.sql")
                .build();
        return db;
    }

    /**
     *
     * @param dataSource to embedded database
     * @return jdbcTemplate object
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}