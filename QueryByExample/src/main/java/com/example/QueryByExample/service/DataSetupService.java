package com.example.QueryByExample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Service
public class DataSetupService implements CommandLineRunner {

    @Value("classpath:init.sql")
    private Resource initSql;

    private final R2dbcEntityTemplate entityTemplate;

    public DataSetupService(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Read the SQL file
        String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);

        // Execute the SQL query
        entityTemplate.getDatabaseClient()
                .sql(query)
                .then()
                .doOnError(error -> System.err.println("Error executing SQL: " + error.getMessage()))
                .doOnSuccess(result -> System.out.println("SQL executed successfully"))
                .subscribe();
    }
}
