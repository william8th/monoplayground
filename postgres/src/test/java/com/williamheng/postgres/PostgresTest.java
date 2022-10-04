package com.williamheng.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;

public class PostgresTest {

    private static DataSource dataSource;
    private static Jdbi jdbi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        var config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:15432/postgres");
        config.setUsername("postgres");
        config.setPassword("password");
        dataSource = new HikariDataSource(config);
        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dataSource.getConnection().close();
    }

    @Test
    public void test() {
        jdbi.withExtension(LockDao.class, dao -> dao.tryLock(1));
        while(true);
    }
}
