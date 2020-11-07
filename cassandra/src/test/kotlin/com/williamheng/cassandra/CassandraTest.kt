package com.williamheng.cassandra

import com.datastax.driver.core.*
import com.google.common.io.Resources
import org.junit.Test

class CassandraTest {

    val cluster = Cluster.builder()
        .addContactPoint("localhost")
        .withPoolingOptions(PoolingOptions().setMaxConnectionsPerHost(HostDistance.LOCAL, 1))
        .build()
        .init()

    @Test
    fun createKeyspace() {
        cluster.connect().use { session ->
            session.execute(
                """
                CREATE KEYSPACE hotel
                    WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
            """.trimIndent()
            )
        }
    }

    @Test
    fun createSchema() {
        val statements = Resources.getResource("hotel.cql")
            .readText()
            .split(";")
            .map { it.replace("\\s+", " ").replace("\n", "").trim() }
            .filter { it.isNotBlank() }
            .map { SimpleStatement(it) }

        cluster.connect("hotel").use { session ->
            statements.forEach {
                session.execute(it)
            }
        }
    }

    @Test
    fun insertSimpleStatement() {
        cluster.connect("hotel").use { session ->
            val statement = SimpleStatement(
                "INSERT INTO hotels (hotel_id, name, phone) VALUES (?,?,?)",
                "AZ123",
                "Super Duper Hotel",
                "1-888-999-9999"
            )
            session.execute(statement)
        }
    }

    @Test
    fun simpleQuery() {
        cluster.connect("hotel").use { session ->
            session.execute("SELECT * from hotel.hotels")
        }

    }

}