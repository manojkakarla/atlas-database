package com.atlas.database;

import com.google.common.io.Resources;

import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.junit.DropwizardAppRule;

public class DbMigrateTest {

    public static final String CONFIG_PATH = Resources.getResource("config.yml").getPath();
    @ClassRule
    public static final DropwizardAppRule<DatabaseConfig> RULE = new DropwizardAppRule<>(DbMigrate.class, CONFIG_PATH);


    @Test
    public void testMigration() throws Exception {
        RULE.getApplication().run(new String[]{"db", "test", CONFIG_PATH});
    }
}