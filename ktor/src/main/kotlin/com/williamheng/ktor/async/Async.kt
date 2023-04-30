package com.williamheng.ktor.async

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation

class Async

data class Foobar(val value: String)
data class Combar(val value1: String, val value2: String)

fun main() {

    val log = LoggerFactory.getLogger(Async::class.java)
    val port = 8080
    val client = HttpClient {
        install(ClientContentNegotiation) {
            jackson()
        }
    }

    embeddedServer(Netty, port = 8080) {

        install(ContentNegotiation) {
            jackson {
                // Do nothing
            }
        }

        routing {
            get("delay1") {
                delay(1000L)
                call.respond(Foobar("delay1"))
            }

            get("delay2") {
                delay(2000L)
                call.respond(Foobar("delay2"))
            }

            get("combo") {
                val startTime = System.currentTimeMillis()
                val delay1Value = client.get("http://localhost:$port/delay1").body<Foobar>()
                val delay2Value = client.get("http://localhost:$port/delay2").body<Foobar>()
                val duration = System.currentTimeMillis() - startTime
                log.info("Response took $duration ms")
                call.respond(Combar(delay1Value.value, delay2Value.value))
            }

            get("async") {
                val startTime = System.currentTimeMillis()
                val delay1Value = async { client.get("http://localhost:$port/delay1").body<Foobar>() }
                val delay2Value = async { client.get("http://localhost:$port/delay2").body<Foobar>() }
                val response = Combar(delay1Value.await().value, delay2Value.await().value)
                val duration = System.currentTimeMillis() - startTime
                log.info("Response took $duration ms")
                call.respond(response)
            }
        }
    }.start()

}
