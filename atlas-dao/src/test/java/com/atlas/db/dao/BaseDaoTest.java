package com.atlas.db.dao;

import com.atlas.core.AtlasAppBuilder;
import com.atlas.core.dw.AtlasApp;
import com.atlas.core.testing.AtlasAppRule;
import com.atlas.database.DatabaseConfig;
import com.atlas.database.DbMigrate;
import com.atlas.db.config.DbConfig;
import com.atlas.infrastructure.SpringBundle;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import io.dropwizard.testing.junit.DropwizardAppRule;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;
import java.util.function.Function;

public abstract class BaseDaoTest {

    public static final String CONFIG_PATH = Resources.getResource("test-config.yml").getPath();
    public static final String CONFIG_PATH2 = Resources.getResource("test-config2.yml").getPath();

    @ClassRule
    public static final DropwizardAppRule<DatabaseConfig> DB_RULE = new DropwizardAppRule<>(DbMigrate.class, CONFIG_PATH);
    @ClassRule
    public static final AtlasAppRule<DatabaseConfig> TEST_RULE = new AtlasAppRule(AtlasApp.class, buildConfig(), CONFIG_PATH2);

    private static SpringBundle<DatabaseConfig> buildConfig() {
        Function<DatabaseConfig, Map<String, Object>> beans = appConfig -> ImmutableMap.of(
                "database", appConfig.getDatabase());
        return new AtlasAppBuilder<DatabaseConfig>()
                .withSpring(beans, DbConfig.class).buildConfig();
    }

    protected static ConfigurableApplicationContext context;
    protected static Handle handle;

    @BeforeClass
    public static void up() throws Exception {
        DB_RULE.getApplication().run("db", "migrate", CONFIG_PATH);
        context = ((AtlasApp) TEST_RULE.getApplication()).getContext();
        context.refresh();
        DataSource ds = DB_RULE.getConfiguration().getDatabase().build(DB_RULE.getEnvironment().metrics(), "migrations");
        try (Connection connection = ds.getConnection()) {
            Liquibase migrator = new Liquibase("test-schema.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
            migrator.update("");
        }
        DBI dbi = context.getBean(DBI.class);
        handle = dbi.open();
    }


    @AfterClass
    public static void tearDown() throws Exception {
        if (handle != null) {
            handle.close();
        }

    }
}