package com.williamheng.guice

import com.google.inject.AbstractModule
import com.google.inject.Guice
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

class A @Inject constructor(val b: B)

@Singleton
class B @Inject constructor(val c: C)

interface C

@Singleton
class CImpl: C

class GuiceTest {

    @Test
    fun testGuice() {
        val registry = Guice.createInjector(
            object: AbstractModule() {
                override fun configure() {
                    bind(A::class.java).asEagerSingleton()
                    bind(C::class.java).to(CImpl::class.java)
                }
            }
        )

        val a = registry.getInstance(A::class.java)
        assertThat(a, notNullValue())
        assertThat(a.b, notNullValue())
        assertThat(a.b.c, notNullValue())
    }
}

