package com.williamheng.disruptor

import com.lmax.disruptor.EventFactory
import com.lmax.disruptor.EventHandler
import com.lmax.disruptor.dsl.Disruptor
import com.lmax.disruptor.util.DaemonThreadFactory
import org.junit.Test
import java.nio.ByteBuffer

class DisruptorTest {

    private class LongEvent(var value: Long? = null)

    @Test
    fun test() {
        val eventFactory = EventFactory { LongEvent() }
        val consumer = EventHandler { event: LongEvent, sequence: Long, endOfBatch: Boolean -> println(event.value) }

        val disruptor = Disruptor(
            eventFactory,
            1024,
            DaemonThreadFactory.INSTANCE
        )

        disruptor.handleEventsWith(consumer)

        disruptor.start()

        val ringBuffer = disruptor.ringBuffer

        val bb = ByteBuffer.allocate(8)
        for (l in 0L until 8L) {
            bb.putLong(0, l)
            ringBuffer.publishEvent({ event: LongEvent, sequence: Long, b: ByteBuffer ->
                event.value = b.getLong(0)
            }, bb)
            Thread.sleep(1000)
        }
    }

}