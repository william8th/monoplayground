package com.williamheng.disruptor

import org.junit.Test
import sun.misc.Unsafe
import java.lang.reflect.Field
import java.util.concurrent.locks.ReentrantLock

class ThreadTest {

    private val U: Unsafe
    private val COUNTER_FIELD_OFFSET: Long
    private val target = 500_000_000L

    init {
        val unsafeField: Field = Unsafe::class.java.getDeclaredField("theUnsafe")
        unsafeField.isAccessible = true
        U = unsafeField.get(null) as Unsafe
        COUNTER_FIELD_OFFSET = U.objectFieldOffset(this::class.java.getDeclaredField("counter"))
    }

    private var counter = 0L

    @Volatile private var volatileCounter = 0L

    @Test
    fun singleThread() {
        measure {
            while (counter < target) {
                counter++
            }
        } // average of 356ms
        assert(counter == target)
    }

    @Test
    fun singleThreadWithLock() {
        measure {
            val lock = ReentrantLock()
            while (true) {
                lock.lock()
                if (counter < target) {
                    counter++
                }
                else {
                    lock.unlock()
                    break
                }
                lock.unlock()
            }
        } // average of 8034ms
        assert(counter == target)
    }

    @Test
    fun twoThreadsWithLock() {
        val lock = ReentrantLock()
        val job = {
            while (true) {
                lock.lock()
                if (counter < target) {
                    counter++
                } else {
                    lock.unlock()
                    break
                }
                lock.unlock()
            }
        }

        val t1 = Thread(job)
        val t2 = Thread(job)

        measure {
            t1.start()
            t2.start()

            t1.join()
            t2.join()
        }  // average of 40_584ms

        assert(counter == target)
    }

    @Test
    fun singleThreadedCAS() {
        measure {
            while (true) {
                if (counter < target) {
                    U.compareAndSwapLong(this, COUNTER_FIELD_OFFSET, counter, counter + 1)
                } else {
                    break
                }
            }
        } // average of 4095ms
        assert(counter == target)
    }

    @Test
    fun twoThreadsWithCAS() {
        val job = {
            while (true) {
                if (counter < target) {
                    U.compareAndSwapLong(this, COUNTER_FIELD_OFFSET, counter, counter + 1)
                } else {
                    break
                }
            }
        }

        val t1 = Thread(job)
        val t2 = Thread(job)

        measure {
            t1.start()
            t2.start()

            t1.join()
            t2.join()
        } // average of 20_477ms
        assert(counter == target)
    }

    @Test
    fun singleThreadVolatile() {
        measure {
            while (true) {
                if (volatileCounter < target) {
                    volatileCounter++
                } else {
                    break
                }
            }
        } // average of 3336ms
        assert(volatileCounter == target)
    }

    private fun measure(closure: () -> Unit) {
        val start = System.currentTimeMillis()
        closure()
        val duration = System.currentTimeMillis() - start
        println("Operation took ${duration}ms")
    }

}
