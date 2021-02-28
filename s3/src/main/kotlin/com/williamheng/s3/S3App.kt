package com.williamheng.s3

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


object S3App {

    @JvmStatic
    fun main(args: Array<String>) {
        val server = embeddedServer(Netty, commandLineEnvironment(args))
        server.start()
    }

}

fun Application.bootstrap() {

    install(ContentNegotiation)
    install(DefaultHeaders)

    routing {
        get("/") {
            call.respond("Hello world")
        }

        post("/") {
            //
        }
    }

}