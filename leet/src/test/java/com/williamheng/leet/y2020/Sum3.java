package com.williamheng.leet.y2020;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Sum3 {

    /**
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * Notice that the solution set must not contain duplicate triplets.
     */

    @Test
    public void test() {
        assertThat(threeSum(new int[]{-1, 0, 1, 2, -1, -4}), equalTo(
                asList(
                        asList(-1, -1, 2),
                        asList(-1, 0, 1)
                )
        ));

        assertThat(threeSum(new int[]{-1, 0, 1, 2, 0, -1, -4}), equalTo(
                asList(
                        asList(-1, -1, 2),
                        asList(-1, 0, 1)
                )
        ));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        Set<List<Integer>> results = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) break;

            int target = -nums[i];
            int front = i+1;
            int back = nums.length-1;

            while (front < back) {
                int num1 = nums[i];
                int num2 = nums[front];
                int num3 = nums[back];
                int sum = num2 + num3;

                if (sum < target) {
                    front++;
                } else if (sum > target) {
                    back--;
                } else {
                    results.add(Arrays.asList(num1, num2, num3));

                    while(front < back && nums[front] == num2) front++;

                    while(back > front && nums[back] == num3) back--;
                }
            }

            while(i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
        }

        return new ArrayList<>(results);
    }

}
