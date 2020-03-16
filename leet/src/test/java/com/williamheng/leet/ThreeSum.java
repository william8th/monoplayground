package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ThreeSum {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, null},
                {new int[]{}, null},
                {
                        new int[]{-1, 0, 1, 2, -1, -4},
                        new int[][]{
                                new int[]{-1, -1, 2},
                                new int[]{-1, 0, 1}
                        }
                }
        });
    }

    @Parameter
    public int[] input;

    @Parameter(1)
    public int[][] expected;

    @Test
    public void test() {
        assertThat(threeSum(input), equalTo(expected));
    }

    int[][] threeSum(int[] input) {

        if (input == null || input.length <= 2) return null;

        Arrays.sort(input);
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < input.length-2; i++) {

            if (i > 0 && input[i] == input[i-1]) {
                continue;
            }

            int start = i+1;
            int end = input.length-1;

            while (start < end) {

                int sum = input[i] + input[start] + input[end];
                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    result.add(new int[]{input[i], input[start], input[end]});

                    while (start < end && input[start] == input[start+1]) start += 1;
                    while (start < end && input[end] == input[end-1]) end -= 1;

                    start += 1;
                    end -= 1;
                }
            }

        }

        int[][] returnValue = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            returnValue[i] = result.get(i);
        }
        return returnValue;
    }

}
