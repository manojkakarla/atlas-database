package com.atlas.database;


import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DbMigrate extends Application<DatabaseConfig> {
    public static void main(String[] args) throws Exception {
        new DbMigrate().run(args);
    }

    @Override
    public void initialize(Bootstrap<DatabaseConfig> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<DatabaseConfig>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DatabaseConfig configuration) {
                return configuration.getDatabase();
            }

        });
    }

    @Override
    public void run(DatabaseConfig databaseConfig, Environment environment) throws Exception {
        environment.jersey().disable();
    }
}
