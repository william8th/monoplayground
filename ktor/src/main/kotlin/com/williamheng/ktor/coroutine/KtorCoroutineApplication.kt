package com.williamheng.ktor.coroutine

import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) {
    val server = embeddedServer(Netty, commandLineEnvironment(args))
    server.start()

    try {
        runBlocking {

            val jobs = launch(Dispatchers.Unconfined) {
                val consumer1 = launch {
                    Consumer {
                        println("Consumer 1 ${Thread.currentThread().name}")
                    }.run()
                }

                val consumer2 = launch(Dispatchers.IO) {
                    Consumer {
                        println("Consumer 2 ${Thread.currentThread().name}")
                        throw Exception()
                    }.run()
                }
            }

            Runtime.getRuntime().addShutdownHook(
                object: Thread() {
                    override fun run() {
                        jobs.cancelChildren()
                        println("Shutting down")
                    }
                }
            )

            jobs.join()

        }
    } catch (e: Exception) {
        println(e)
    } finally {
        println("Exit")
    }
}

class Consumer(private val action: () -> Unit) {

    suspend fun run() {
        while (true) {
            action()
            delay(1000)
        }
    }

}
