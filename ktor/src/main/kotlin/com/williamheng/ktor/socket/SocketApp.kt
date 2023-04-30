package com.williamheng.ktor.socket

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket


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
                                if (text.toLowerCase() == "bye") {
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
