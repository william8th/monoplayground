package com.williamheng.codility

import org.junit.Test
import java.math.BigInteger

class CombinationTest {

    @Test
    fun shouldDo() {
//        assert(toVelocityArray(intArrayOf(2)).contentEquals(intArrayOf()))
//        assert(toVelocityArray(intArrayOf(2, 1)).contentEquals(intArrayOf(-1)))
//        assert(toVelocityArray(intArrayOf(2, 2, 2, 2)).contentEquals(intArrayOf(0, 0, 0)))
//        assert(toVelocityArray(intArrayOf(-1, 1, 3, 3, 3, 2, 3, 2, 1, 0)).contentEquals(intArrayOf(2,2,0,0,-1,1,-1,-1,-1)))

//        assert(combinations(9999) == 49985001)

        assert(solution(intArrayOf(2, 1)) == 0)
        assert(solution(intArrayOf(-1, 1, 3, 3, 3, 2, 3, 2, 1, 0)) == 5)
        assert(solution((1..10000).map { 2 }.toIntArray()) == 49985001)
    }

    val MAXIMUM_COMBINATION = 1_000_000_000
    fun solution(A: IntArray): Int {

        // We need to first figure out the movements between data points and it's easier to compute an array of differences
        // between two elements to get an array of velocity
        val velocityArray = toVelocityArray(A)

        if (velocityArray.size < 2) return 0 // Not

        // We just need to loop through and find how many contiguous stable time points there are
        // We can then use the combination formula to find out how many possible pairs of stable time points
        var totalCombinations = 0
        var token = velocityArray[0]
        var numberOfElements = 1
        var i = 1
        while(i < velocityArray.size) {

            val nextVelocity = velocityArray[i]
            if (nextVelocity == token) {
                numberOfElements++
            } else {

                // Reached the end of the search
                if (numberOfElements > 1) {
                    totalCombinations += combinations(numberOfElements)
                }

                // Start a new search
                numberOfElements = 1
                token = nextVelocity
            }

            if (totalCombinations >= MAXIMUM_COMBINATION) return MAXIMUM_COMBINATION
            i++

            // We have reached the end of the array
            if (i == velocityArray.size) {
                if (numberOfElements > 1) {
                    totalCombinations += combinations(numberOfElements)
                }
            }

        }

        return totalCombinations
    }

    private fun toVelocityArray(A: IntArray): IntArray {
        val velocityArray = mutableListOf<Int>()
        val maxIndex = A.size - 1
        for ((key, value) in A.withIndex()) {
            if (key > maxIndex-1) break
            velocityArray.add(A[key+1] - value)
        }

        return velocityArray.toIntArray()
    }

    private fun combinations(n: Int): Int {
        return n * (n - 1) / 2
    }

}