package com.williamheng.leet.y2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TwoSum {

    @Test
    public void test() {
        assertThat(twoSum(new int[]{2, 7, 11, 5}, 9), equalTo(new int[]{0, 1}));
        assertThat(twoSum(new int[]{2, 3, 5, 6, 2}, 4), equalTo(new int[]{0, 4}));
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalStateException("Must have one solution");
    }

}
