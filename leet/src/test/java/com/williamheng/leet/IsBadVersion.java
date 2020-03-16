package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class IsBadVersion {

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {0, 0},
                {1000, 523},
                {423, 422}
        });
    }

    @Parameter
    public int input;

    @Parameter(1)
    public int expected;

    @Test
    public void test() {
        assertThat(findBadVersion(input), equalTo(expected));
    }

    boolean isBadVersion(int x) {
        return x >= expected;
    }

    int findBadVersion(int n) {
        if (n <= 1) return 0;

        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return end;
    }

}
