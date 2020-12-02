package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class WidestVerticalArea {

    @Test
    public void test() {
        int[][] points = new int[][]{
                new int[]{8, 7},
                new int[]{9, 9},
                new int[]{7, 4},
                new int[]{9, 7},
        };
        assertThat(maxWidthOfVerticalArea(points), equalTo(1));
    }

    @Test
    public void test2() {
        int[][] points = new int[][]{
                new int[]{3, 1},
                new int[]{9, 0},
                new int[]{1, 0},
                new int[]{1, 4},
                new int[]{5, 3},
                new int[]{8, 8},
        };
        assertThat(maxWidthOfVerticalArea(points), equalTo(3));
    }

    @Test
    public void test3() {
        // [[1,5],[1,70],[3,1000],[55,700],[999876789,53],[987853567,12]]
        int[][] points = new int[][]{
                new int[]{1, 5},
                new int[]{1, 70},
                new int[]{3, 1000},
                new int[]{55, 700},
                new int[]{999876789, 53},
                new int[]{987853567, 12},
        };
        assertThat(maxWidthOfVerticalArea(points), equalTo(987853512));
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        int max = 0;
        int[] x = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
        }
        Arrays.sort(x);
        for (int i = points.length - 1; i > 0; i--) {
            max = Math.max(x[i] - x[i-1], max);
        }
        return max;
    }

}
