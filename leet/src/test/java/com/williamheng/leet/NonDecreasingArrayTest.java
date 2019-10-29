package com.williamheng.leet;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NonDecreasingArrayTest {

    @Test
    public void testNonDecreasingArray() {
        assertThat(checkPossibility(new int[]{}), equalTo(false));
        assertThat(checkPossibility(new int[]{1}), equalTo(true));
        assertThat(checkPossibility(new int[]{4, 2, 3}), equalTo(true));
        assertThat(checkPossibility(new int[]{4, 2, 1}), equalTo(false));
        assertThat(checkPossibility(new int[]{3, 2, 4}), equalTo(true));
        assertThat(checkPossibility(new int[]{1, 2, 3, 4}), equalTo(true));
        assertThat(checkPossibility(new int[]{0, 0, 0}), equalTo(false));
        assertThat(checkPossibility(new int[]{3, 4, 2, 3}), equalTo(false));
    }

    boolean checkPossibility(int[] nums) {
        if (nums.length == 0) return false;

        int modified = 0;
        for (int i = 1; i < nums.length; i++) {
            int previousValue = nums[i-1];
            int currentValue = nums[i];
            if (currentValue <= previousValue) {
                currentValue = previousValue + 1;
                modified++;
            }

            nums[i-1] = previousValue;
            nums[i] = currentValue;

            if (modified > 1) return false;
        }

        return true;
    }
}