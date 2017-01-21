package com.atlas.db.config;


import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Bean
    public DBI dbi() {
        final DBIFactory factory = new DBIFactory();
        return factory.build(environment, dataSourceFactory, "db");
    }
}