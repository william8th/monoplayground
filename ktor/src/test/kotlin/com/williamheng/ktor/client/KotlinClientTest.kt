package com.williamheng.ktor.client

import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.util.AttributeKey
import org.junit.Test

class KotlinClientTest {

    data class MyClientConfig(
        var oauthUrl: String,
        var client: HttpClient = HttpClient(Apache) {
            install(JsonFeature)
        }
    )

    class MyFeature(private val config: MyClientConfig) {

        suspend fun getAccessToken(): String {
            return if (token != null) {
                token
            } else {
                val httpResponse = config.client.post<HttpResponse>("http://localhost:8080")
                return when(httpResponse.status) {
                    HttpStatusCode.OK -> httpResponse.receive()
                    else -> throw RuntimeException("Not getting stuff")
                }
            }
        }

        companion object: HttpClientFeature<MyClientConfig, MyFeature> {
            val token: String? = null

            override val key: AttributeKey<MyFeature>
                get() = AttributeKey("MyFeature")

            override fun install(feature: MyFeature, scope: HttpClient) {
                scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                    val accessToken = feature.getAccessToken()
                    this.context.headers.append(HttpHeaders.Authorization, accessToken)
                }
            }

            override fun prepare(block: MyClientConfig.() -> Unit): MyFeature {
                return MyFeature(MyClientConfig("").apply(block))
            }
        }

    }

    @Test
    fun testClient() {
        HttpClient(Apache) {
            install(MyFeature) {
                oauthUrl = ""
            }
        }


    }

}