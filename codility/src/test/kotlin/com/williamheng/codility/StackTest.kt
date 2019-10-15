package com.williamheng.codility

import org.junit.Test
import java.util.ArrayDeque

class StackTest {

    @Test
    fun shouldDo() {
        assert(solution("-34") == -1)
        assert(solution("-") == -1)
        assert(solution("+") == -1)
        assert(solution("DUP") == -1)
        assert(solution("1 POP") == -1)
        assert(solution("13 DUP 4 POP 5 DUP + DUP + -") == 7)
    }

    private val stack = ArrayDeque<Int>()
    private val ERROR_RETURN_CODE = -1
    private val OPERATION_DELIMITER = " "
    fun solution(S: String): Int {
        S.split(OPERATION_DELIMITER)
            .forEach { operation ->
                try {
                    parseOperation(operation)
                } catch (e: Exception) {
                    return ERROR_RETURN_CODE
                }
            }

        return if (stack.size == 0) ERROR_RETURN_CODE else stack.pop()
    }

    private fun parseOperation(operation: String) {
        when(operation) {
            "DUP" -> duplicate()
            "POP" -> pop()
            "+" -> addition()
            "-" -> subtraction()
            else -> storeInteger(operation)
        }
    }

    private fun duplicate() {
        val top = stack.peek()
        stack.push(top)
    }

    private fun pop() {
        stack.pop()
    }

    private fun addition() {
        val a = stack.pop()
        val b = stack.pop()
        stack.push(a + b)
    }

    private fun subtraction() {
        val a = stack.pop()
        val b = stack.pop()
        stack.push(a - b)
    }

    private fun storeInteger(s: String) {
        val integer = s.toInt()
        if (integer < 0) throw IllegalStateException("Unexpected negative number")
        stack.push(integer)
    }

}