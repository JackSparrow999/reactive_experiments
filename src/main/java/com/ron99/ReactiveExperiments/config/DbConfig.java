package com.ron99.ReactiveExperiments.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DbConfig extends AbstractR2dbcConfiguration {

    @Bean
    public ConnectionFactory connectionFactory(){
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "pool")
                        .option(PROTOCOL, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "postgres")
                        .option(PASSWORD, "ronaq")
                        .option(DATABASE, "twitter")
                        .build()
        );
    }

    @Bean
    public DatabaseClient databaseClient(@Autowired ConnectionFactory connectionFactory){
        return DatabaseClient.create(connectionFactory);
    }

}
