package ru.tentbest.app.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
@Configuration
public class R2DBCConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER,"postgresql")
                        .option(HOST,"localhost")
                        .option(USER,"postgres")
                        .option(PASSWORD,"root")
                        .option(PORT,5432)
                        .option(DATABASE,"app")
                .build());
    }

    @Bean
    public DatabaseClient databaseClient(){
        return DatabaseClient.create(connectionFactory());
    }
}
