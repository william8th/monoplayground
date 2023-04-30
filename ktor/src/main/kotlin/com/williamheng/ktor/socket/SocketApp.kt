package com.williamheng.ktor.socket

import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText


fun main() {
    embeddedServer(Netty, applicationEngineEnvironment {

        connector {
            port = 8080
        }

        module {
            install(WebSockets)
            install(CallLogging)

            routing {
                get("/") {
                    call.respondText("OK")
                }

                webSocket("/") {
                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> {
                                val text = frame.readText()
                                outgoing.send(Frame.Text("You sent: $text"))
                                if (text.lowercase() == "bye") {
                                    close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }).start()
}

