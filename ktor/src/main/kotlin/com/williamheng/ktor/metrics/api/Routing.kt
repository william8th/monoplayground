package com.williamheng.ktor.metrics.api

import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.routing() {
    routing {
        get("/add") {
            call.respondText("Add", ContentType.Text.Plain)
        }
    }
}