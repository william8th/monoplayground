package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ContiguousSum {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, 0, null},
                {new int[]{}, 0, null},
                {new int[]{1}, 0, null},
                {new int[]{1}, 1, new int[]{1}},
                {new int[]{1, 2, 3, 4}, 4, new int[]{4}},
                {new int[]{1, 2, 3, 4}, 5, new int[]{2, 3}},
                {new int[]{1, 2, 3, 4}, 11, null},
                {new int[]{1, 2, 3, 4}, 10, new int[]{1, 2, 3, 4}},
                {new int[]{4, 3, 2, 1}, 10, new int[]{4, 3, 2, 1}},
                {new int[]{4, 3, 2, 1, 5, 6, 7, 23, 45, 500, 34}, 568, new int[]{23, 45, 500}},
        });
    }

    @Parameter
    public int[] input;

    @Parameter(1)
    public int total;

    @Parameter(2)
    public int[] expected;

    @Test
    public void test() {
        assertThat(contiguousSum(input, total), equalTo(expected));
    }

    int[] contiguousSum(int[] input, int total) {

        if (input == null || input.length == 0 || total == 0) return null;

        for (int i = 0; i < input.length; i++) {

            int sum = input[i];

            if (sum == total) return new int[]{input[i]};

            for (int j = i + 1; j < input.length; j++) {
                sum += input[j];

                if (sum == total) {
                    return Arrays.copyOfRange(input, i, j + 1);
                }
            }
        }

        return null;
    }

}
