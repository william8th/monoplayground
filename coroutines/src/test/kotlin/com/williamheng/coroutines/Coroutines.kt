package com.williamheng.coroutines

import kotlinx.coroutines.*
import org.junit.Test

class Coroutines {

    @Test
    fun basicCoroutine() {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        Thread.sleep(2000L)
    }

    @Test
    fun basicBlockingCoroutine() {
        GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        runBlocking {
            delay(2000L)
        }
    }

    @Test
    fun basicCoroutineBlockingWrap() {
        runBlocking {
            GlobalScope.launch {
                delay(1000L)
                println("World!")
            }
            println("Hello,")
            delay(2000L)
        }
    }

    @Test
    fun waitOnJob() {
        runBlocking {
            val job = GlobalScope.launch {
                delay(1000L)
                println("World!")
            }
            println("Hello,")
            job.join()
        }
    }

    @Test
    fun waitRunBlocking() {
        runBlocking {
            val job = launch {
                delay(1000L)
                println("World")
            }

            val job2 = async {
                delay(1000L)
                println("Hello")
            }

            job.cancel()
            println("End of runBlocking")
        }

        println("End of main")
    }
}