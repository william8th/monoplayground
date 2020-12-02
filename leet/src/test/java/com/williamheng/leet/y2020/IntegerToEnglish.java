package com.williamheng.leet.y2020;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IntegerToEnglish {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {3, "Three"},
                {23, "Twenty Three"},
                {123, "One Hundred Twenty Three"},
                {12345, "One Thousand Three Hundred Forty Five"},
                {1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"},
                {1_234_567_891, "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"},
                {2_147_483_647, "Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven"}
        });
    }

    @Parameterized.Parameter
    public int input;

    @Parameterized.Parameter(1)
    public String output;

    @Test
    public void test() {
        assertThat(numberToWords(input), equalTo(output));
    }

    public String numberToWords(int num) {
        /**
         * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
         * 11, 12, 13, 14, 15, 16, 17, 18, 19 -> 14-19 number+teen
         * 20, 30, 40, 50 -> Twenty, Thirty, Forty, Fifty, ...
         * 2x - 9x -> Twenty One
         * Hundred
         *
         *
         * Number + DP
         *
         *
         * 0-99 -> Single decimal position
         *
         * 100-999 -> Hundreds + single decimal position
         *
         * 1000 -> Thousand
         * 10_000 -> Ten Thousand
         * 100_000 -> Hundred Thousand
         *
         * 1_000_000
         */

        if (num == 0) return "Zero";

        int[] basicNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 20, 30, 40, 50, 60, 70, 80, 90};
//        int[] basicNumbers = new int[]{90, 80, }
//        int[] basicWord = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};

        return "";
    }
}
