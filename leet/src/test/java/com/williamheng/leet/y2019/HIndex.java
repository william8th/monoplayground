package com.williamheng.leet.y2019;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class HIndex {

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {null, 0},
                {new int[]{}, 0},
                {new int[]{150}, 1},
                {new int[]{3, 0, 6, 1, 5}, 3}
        });
    }

    @Parameter
    public int[] input;

    @Parameter(1)
    public int expected;

    @Test
    public void test() {
        assertThat(hIndex(input), equalTo(expected));
    }

    int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;

        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < citations.length; i++) {
            int h = n - i;
            if (citations[i] >= h) {
                return h;
            }
        }

        return 0;
    }

}
