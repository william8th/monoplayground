package com.williamheng.postgres;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.jdbi.v3.core.Jdbi;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class EmbeddedPostgres {

    @Rule
    SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    private Jdbi jdbi;

    @Before
    public void setUp() throws Exception {
        jdbi = Jdbi.create(pg.getEmbeddedPostgres().getPostgresDatabase());
    }

    @Test
    public void foobar() {
        jdbi.withHandle(handle -> handle.createUpdate("""
                CREATE TABLE something (id INT, value TEXT)
                """))
                .execute();

        var a = 1;
    }
}
