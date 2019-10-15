package com.williamheng.codility

import org.junit.Test

class RealTest {

    @Test
    fun shouldWhat() {
        assert(solution(intArrayOf(3, 5, 6, 3, 3, 5)) == 4)
        assert(solution(intArrayOf(3)) == 0)
        assert(solution(intArrayOf(3, 5)) == 0)
        assert(solution(intArrayOf(3, 3)) == 1)
        assert(solution(intArrayOf(-1, 3)) == 0)
        assert(solution(intArrayOf(-1, -1)) == 1)
        assert(solution(intArrayOf(-3, -5, -6, -3, -3, -5)) == 4)
    }

    val MAXIMUM_IDENTICAL_PAIRS = 1_000_000
    fun solution(A: IntArray): Int {
        var identicalPairs = 0

        // storage stores a map of value -> indices where this value has occurred
        val storage = mutableMapOf<Int, MutableCollection<Int>>()

        for ((i, value) in A.withIndex()) {
            val indices = storage.getOrDefault(value, mutableListOf())
            identicalPairs += indices.size

            indices.add(i)
            storage[value] = indices // Store that an index of this value is seen

            if (identicalPairs >= MAXIMUM_IDENTICAL_PAIRS) return MAXIMUM_IDENTICAL_PAIRS
        }
        return identicalPairs
    }
}