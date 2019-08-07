package com.williamheng.ktor.api

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.routing() {
    routing {
        get("/add") {
            call.respondText("Add", ContentType.Text.Plain)
        }
    }
}