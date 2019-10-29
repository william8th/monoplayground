package com.williamheng.leet

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ValidNumber {

    @Test
    fun testValidNumber() {
        assertThat(isNumber("0"), equalTo(true))
        assertThat(isNumber(" 0.1"), equalTo(true))
        assertThat(isNumber("abc"), equalTo(false))
        assertThat(isNumber("1 a"), equalTo(false))
        assertThat(isNumber("2e10"), equalTo(true))
        assertThat(isNumber(" -90e3"), equalTo(true))
        assertThat(isNumber(" le"), equalTo(false))
        assertThat(isNumber("e3"), equalTo(false))
        assertThat(isNumber(" 6e-1"), equalTo(true))
        assertThat(isNumber(" 99e2.5"), equalTo(false))
        assertThat(isNumber("53.5e93"), equalTo(true))
        assertThat(isNumber("--6"), equalTo(false))
        assertThat(isNumber("-+3"), equalTo(false))
        assertThat(isNumber("95a54e53"), equalTo(false))
        assertThat(isNumber("."), equalTo(false))
        assertThat(isNumber(" "), equalTo(false))
        assertThat(isNumber("e."), equalTo(false))
        assertThat(isNumber(".e"), equalTo(false))
        assertThat(isNumber(".1"), equalTo(true))
        assertThat(isNumber("3."), equalTo(true))
        assertThat(isNumber(" +0e-"), equalTo(false))
//        assertThat(isNumber("46.e3"), equalTo(true))
    }

    fun isNumber(s: String): Boolean {
        var word = s.trim()

        if (word.isBlank()) return false

        // Determine positive negative sign
        if (word.first() == '+' || word.first() == '-') {
            word = word.substring(1)
        }

        // Split into number vs decimal
        val decimals = word.split(".")

        return when (decimals.size) {
            1 -> {
                // No decimal
                val preDecimal = decimals.first()
                preDecimal.isNotBlank() && checkPreDecimal(preDecimal)
            }
            2 -> {
                // There's a decimal place
                val preDecimal = decimals.first()
                val postDecimal = decimals[1]

                // PreDecimal cannot contain exponential
                return when {
                    preDecimal.isBlank() -> checkPostDecimal(postDecimal)
                    postDecimal.isBlank() -> checkPreDecimal(preDecimal)
                    preDecimal.contains('e') -> false
                    else -> preDecimal.all { it.isDigit() } && checkPostDecimal(postDecimal)
                }
            }
            else -> return false
        }

    }

    fun checkPreDecimal(number: String): Boolean {
        if (number.startsWith("e") || number.endsWith("e")) return false

        val exponents = number.split("e")
        return when (exponents.size) {
            1 -> exponents.first().all { it.isDigit() }
            2 -> {
                val preExponent = exponents.first()
                var postExponent = exponents[1]

                return if (!preExponent.all { it.isDigit() }) {
                    false
                } else {
                    if (postExponent.first() == '-') {
                        postExponent = postExponent.substring(1)
                    }

                    postExponent.isNotBlank() && postExponent.all { it.isDigit() }
                }
            }
            else -> false
        }
    }

    fun checkPostDecimal(number: String): Boolean {
        val exponentials = number.split("e")
        return when (exponentials.size) {
            1 -> {
                // No exponential
                val decimal = exponentials.first()
                decimal.isNotBlank() && decimal.all { it.isDigit() }
            }
            2 -> {
                // Exponential exists
                exponentials.all { it.isNotBlank() && it.all { it.isDigit() } }
            }
            else -> false
        }
    }

}