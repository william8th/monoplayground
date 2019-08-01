package com.williamheng.aurora

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.junit.Before
import org.junit.Test
import org.slf4j.LoggerFactory

import java.time.Duration

class AuroraTest {

    def log = LoggerFactory.getLogger(this.class)

    def LOCALHOST_CONFIG = "src/test/resources/local.properties"
    def AURORA_CONFIG = "src/test/resources/aurora.properties"

    Jdbi jdbi

    @Before
    void setUp() throws Exception {
        def config = new HikariConfig(LOCALHOST_CONFIG)
        def dataSource = new HikariDataSource(config)
        jdbi = Jdbi.create(dataSource)
        jdbi.installPlugin(new SqlObjectPlugin())
    }

    @Test
    void "test connection"() {
        createTableIfNotExists()
    }

    @Test
    void "insert random data"() {
        insertRandomUser()
    }

    @Test
    void "continuous random data insertion"() {
        createTableIfNotExists()
        log.info("Starting loop...")
        while (true) {
            insertRandomUser()
            Thread.sleep(Duration.ofSeconds(1).toMillis())
        }
    }

    private void createTableIfNotExists() {
        log.info("Creating table...")
        def duration = measured {
            jdbi.withExtension(AuroraDao.class, { dao ->
                dao.createTable()
            })
        }
        log.info("Done in ${duration.toMillis()}ms.")
    }

    private void insertRandomUser() {
        def userId = UUID.randomUUID().toString()
        log.info("Inserting random user userId=$userId")
        def duration = measured {
            jdbi.withExtension(AuroraDao.class, { dao ->
                def name = userId
                dao.insertUser(name)
            })
        }
        log.info("Done in ${duration.toMillis()}ms.")
    }

    private static Duration measured(Closure closure) {
        def startTime = System.nanoTime()
        closure.run()
        def duration = System.nanoTime() - startTime
        return Duration.ofNanos(duration)
    }

}
