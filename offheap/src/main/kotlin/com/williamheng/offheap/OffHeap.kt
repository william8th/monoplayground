package com.williamheng.offheap

import java.nio.ByteBuffer

object OffHeap {

    val bb = ByteBuffer.allocateDirect(10_000)

    val r = {
        val lbb = bb.duplicate()

        val threadName = Thread.currentThread().name
        val threadNum = Integer.valueOf(threadName)
        val position = threadNum * java.lang.Long.BYTES
        lbb.position(position)
        lbb.mark()
        while (true) {
            val value = "${threadNum+1}${System.nanoTime()}".toLong()
            lbb.putLong(value)
            lbb.reset()
            Thread.yield()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val maxThread = 9
        for (threadNum in 0..maxThread) {
            val t = Thread(r)
            t.name = "$threadNum"
            t.start()
        }

        while (true) {
            for (t in 0..maxThread) {
                bb.position(t * java.lang.Long.BYTES)
                val value = bb.getLong()
                println(value)
            }
            println()
            Thread.yield()
        }
    }
}
