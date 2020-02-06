package com.williamheng.ktor.jdbi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import org.jdbi.v3.testing.JdbiRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class KtorJdbiTest {

    @Rule
    @JvmField
    val postgres = JdbiRule.embeddedPostgres()

    lateinit var jdbi: Jdbi

    @Before
    fun setUp() {
        jdbi = postgres.getJdbi().installPlugins()
        jdbi.withHandleUnchecked { handle ->
            handle.createUpdate("CREATE TABLE IF NOT EXISTS users(id BIGSERIAL PRIMARY KEY, name VARCHAR)")
                .execute()
        }
    }

    @Test
    fun testJdbiAsync() {
        val names = (1..1000).map { UUID.randomUUID().toString() }

        val syncStartTime = System.currentTimeMillis()
        names.forEach { insertUser(it) }
        val syncDuration = System.currentTimeMillis() - syncStartTime
        println("Sync operations took $syncDuration ms")

        val asyncStartTime = System.currentTimeMillis()
        runBlocking(Dispatchers.IO) {
            names.map {
                async {
                    insertUser(it)
                }
            }.awaitAll()
        }
        val asyncDuration = System.currentTimeMillis() - asyncStartTime

        println("Async operations took $asyncDuration ms")
    }

    private fun insertUser(name: String) {
        jdbi.withHandleUnchecked { handle ->
            handle.createUpdate("INSERT INTO users(name) VALUES (:name)")
                .bind("name", name)
                .execute()
        }
    }

    data class User(val id: Int, val name: String)
}