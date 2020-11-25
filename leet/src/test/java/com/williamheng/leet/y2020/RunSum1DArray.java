package com.williamheng.leet.y2020;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RunSum1DArray {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                { null, null },
                { new int[]{}, new int[]{} },
                { new int[]{1}, new int[]{1} },
                { new int[]{0, 0, 0}, new int[]{0, 0, 0} },
                { new int[]{1, 2, 3, 4}, new int[]{1, 3, 6, 10} },
                { new int[]{1, 2, 3, -4}, new int[]{1, 3, 6, 2} },
        });
    }

    @Parameterized.Parameter
    public int[] input;

    @Parameterized.Parameter(1)
    public int[] output;

    @Test
    public void test() {
        assertThat(runningSum(input), equalTo(output));
    }

    public int[] runningSum(int[] nums) {
        if (nums == null) return null;
        if (nums.length == 0) return new int[]{};

        int[] result = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result[i] = sum;
        }

        return result;
    }
}
