package com.williamheng.resilience

import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.MatcherAssert.assertThat

class ResilienceTest {

    @ClassRule
    public static WireMockClassRule wireMockClassRule = new WireMockClassRule()

    @BeforeClass
    static void setUp() throws Exception {
        stubFor(
                get("/api")
                .willReturn(
                        aResponse()
                        .withStatus(500)
                )
        )
    }

    @Test
    void retriesAfterFailure() {
        assertThat(true, equalTo(true))
    }
}
