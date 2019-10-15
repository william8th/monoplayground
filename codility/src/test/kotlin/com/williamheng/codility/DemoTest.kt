package com.williamheng.codility

import org.junit.Test

class DemoTest {

    @Test
    fun shouldDo() {
        assert(solution(intArrayOf(1, 3, 6, 4, 1, 2)) == 5)
        assert(solution(intArrayOf(1, 2, 3)) == 4)
        assert(solution(intArrayOf(-1, 0, -2)) == 1)
    }

    fun solution(A: IntArray): Int {
        val sortedValues = A.filter { i -> i > 0 }.sorted()

        // Return 1 if the list only contains zeroes and negative numbers
        if (sortedValues.isEmpty()) return 1

        var i = 0
        var smallestNumber = sortedValues[i] + 1
        while (i < sortedValues.size) {
            i++
            if (i == sortedValues.size) {
                // We have reached the end of the list, return the smallest number
                break
            }

            val nextNumber = sortedValues[i]
            if (nextNumber <= smallestNumber) {
                // The next number is smaller or equal to what I assume to be the smallest number that does not exist in the list,
                // Increase the smallest number by one and skip the next value in the list
                smallestNumber = nextNumber + 1
            } else {
                break
            }
        }
        return smallestNumber
    }
}