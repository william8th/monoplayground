package com.williamheng.leet.y2019

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Reorder {

    @Test
    fun testReorder() {
        assertThat(
            reorderLogFiles(arrayOf("dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero")),
            equalTo(arrayOf("let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"))
        )
    }

    fun reorderLogFiles(logs: Array<String>): Array<String> {
        val letterLogs = mutableListOf<String>()
        val digitLogs = mutableListOf<String>()

        logs.forEach { log ->
            val words = log.split(" ")
            if (words[1][0].isLetter()) {
                letterLogs.add(log)
            } else {
                digitLogs.add(log)
            }
        }

        return sort(letterLogs) + digitLogs
    }

    fun sort(logs: List<String>): Array<String> {
        return logs.map {
            val words = it.split(" ")
            words[0] to words.subList(1, words.size).joinToString(" ")
        }.sortedWith(compareBy({it.second}, {it.first}))
            .map { "${it.first} ${it.second}" }
            .toTypedArray()
    }
}