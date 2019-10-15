package com.williamheng.resilience4j

import io.github.resilience4j.retry.Retry
import io.github.resilience4j.retry.RetryConfig
import io.github.resilience4j.retry.RetryRegistry
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.time.Duration

class Resilience(private val url: String) {

    private val client = HttpClient(HttpClientEngine) {
        install(JsonFeature)
    }
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun hitWithRetry() {

        val retryConfig = RetryConfig.custom<HttpResponse>()
                .maxAttempts(2)
                .waitDuration(Duration.ofMillis(1000))
                .retryOnResult { response -> response.status != HttpStatusCode.OK }
                .build()

        val retryRegistry = RetryRegistry.of(retryConfig)
        retryRegistry.retry("a-name").executeCallable {
            runBlocking {
                log.info("Sending GET request for /api")
                val response = client.get<HttpResponse>(url)
                response.content
            }
        }
    }
}

}
