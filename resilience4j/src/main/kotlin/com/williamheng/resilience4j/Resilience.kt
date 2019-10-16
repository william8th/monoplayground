package com.williamheng.resilience4j

import io.github.resilience4j.retry.RetryConfig
import io.github.resilience4j.retry.RetryRegistry
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.time.Duration

fun main() {
    val resilience = Resilience()
    resilience.retry()
}

class Resilience {

    private val log = LoggerFactory.getLogger(this.javaClass)

    private val client = HttpClient {
        install(JsonFeature)
    }

    val retryConfig = RetryConfig.custom<HttpResponse>()
        .maxAttempts(2)
        .waitDuration(Duration.ofMillis(1000))
        .retryOnResult { response -> response.status != HttpStatusCode.OK }
        .build()

    val retryRegistry = RetryRegistry.of(retryConfig)

    fun retry() {
        runBlocking {
            val retry = retryRegistry.retry("foobar")
            retry.executeCallable {
                val retryAttempt = retry.metrics.numberOfFailedCallsWithRetryAttempt

                log.info("Retrying $retryAttempt")
                if (retryAttempt < 10) {
                    throw Exception("Deliberate failure")
                }
            }
        }
    }

    fun hitWithRetry(url: String) {

        retryRegistry.retry("a-name").executeCallable {
            runBlocking {
                log.info("Sending GET request for /api")
                val response = client.get<HttpResponse>(url)
                response.content
            }
        }
    }
}
