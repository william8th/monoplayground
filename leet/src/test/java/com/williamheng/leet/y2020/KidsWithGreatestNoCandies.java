package com.williamheng.leet.y2020;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class KidsWithGreatestNoCandies {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {null, 0, null},
                {new int[]{1}, 0, singletonList(true)},
                {new int[]{1, 0}, 1, asList(true, true)},
                {new int[]{0, 0, 0}, 1, asList(true, true, true)},
                {new int[]{2, 3, 5, 1, 3}, 3, asList(true, true, true, false, true)},
                {new int[]{4, 2, 1, 1, 2}, 1, asList(true, false, false, false, false)},
                {new int[]{12, 1, 12}, 10, asList(true, false, true)},
        });
    }

    @Parameterized.Parameter
    public int[] candies;

    @Parameterized.Parameter(1)
    public int extraCandies;

    @Parameterized.Parameter(2)
    public List<Boolean> output;

    @Test
    public void test() {
        assertThat(kidsWithCandies(candies, extraCandies), equalTo(output));
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        if (candies == null) return null;
        if (candies.length == 1) return singletonList(true);

        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < candies.length; i++) {

            int potentialCandies = candies[i] + extraCandies;

            boolean isMax = true;
            for (int j = 0; j < candies.length; j++) {
                if (potentialCandies < candies[j]) {
                    isMax = false;
                    break;
                }
            }

            result.add(isMax);
        }

        return result;
    }
}
