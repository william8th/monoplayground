package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SubarraySumEqualsK {

    @Test
    public void test() {
        assertThat(subarraySum(new int[]{1, 1, 1}, 1), equalTo(3));
        assertThat(subarraySum(new int[]{1, 1, 1}, 2), equalTo(2));
        assertThat(subarraySum(new int[]{1, 2, 3}, 3), equalTo(2));
        assertThat(subarraySum(new int[]{1, -1, 0}, 0), equalTo(3));
        assertThat(subarraySum(new int[]{1, 2, 1, 3}, 3), equalTo(3));

        // Explanation: This produces a running sum array of [0, 1, 3, 4, 7]. Put the array in a map, d, mapping runningSum -> frequency
        // d = [
        // 0: 1, (Inserted as a helper, imagine it as seeing runningSum of zero once starting initially)
        // 1: 1,
        // 3: 1,
        // 4: 1,
        // 7: 1
        // ]
        // 3 - 3 => d[0] has count 1, count += 1
        // 4 - 3 => d[1] has count 1, count += 1
        // 7 - 3 => d[4] has count 1, count += 1
        // count = 3
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> runningSumFrequency = new HashMap<>();
        int count = 0;
        int runningSum = 0;
        runningSumFrequency.put(0, 1); // We've seen running sum of 0 once

        for (int num : nums) {
            runningSum += num;
            count += runningSumFrequency.getOrDefault(runningSum - k, 0);
            runningSumFrequency.put(runningSum, runningSumFrequency.getOrDefault(runningSum, 0) + 1);
        }

        return count;
    }
}
