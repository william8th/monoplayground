package com.williamheng.resilience4j

import io.github.resilience4j.kotlin.retry.executeSuspendFunction
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
import java.util.function.Predicate

fun main() {
    val resilience = Resilience()
    resilience.retry()
}

class Resilience {

    private val log = LoggerFactory.getLogger(this.javaClass)

    private val client = HttpClient {
        install(JsonFeature)
    }

    private val retryConfig = RetryConfig.custom<HttpResponse>()
        .maxAttempts(2)
        .waitDuration(Duration.ofMillis(1000))
        .retryOnResult { response -> response.status != HttpStatusCode.OK }
        .build()

    private val noResponseRetryConfig = RetryConfig.custom<Void>()
        .maxAttempts(10)
        .waitDuration(Duration.ofMillis(1000))
        .retryOnException { e -> true }
        .build()

    private val retryRegistry = RetryRegistry.of(
        mapOf(
            "a-retry" to retryConfig,
            "main-retry" to noResponseRetryConfig
        )
    )

    fun retry() {
        val retry = retryRegistry.retry("main-retryer", "main-retry")
        retry.executeCallable {
            log.info("Trying")
            throw Exception("Deliberate failure")
        }
    }

    suspend fun hitWithRetry(url: String) {
        retryRegistry.retry("a-retryer", "a-retry").executeSuspendFunction {
                log.info("Sending GET request for /api")
                val response = client.get<HttpResponse>(url)
        }
    }
}
