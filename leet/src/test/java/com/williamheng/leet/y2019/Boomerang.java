package com.williamheng.leet.y2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Boomerang {

    @Test
    public void testBoomerang() {
        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{0, 0},
                                new int[]{0, 1},
                                new int[]{0, 2}
                        }
                ),
                equalTo(false)
        );

        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{1, 1},
                                new int[]{2, 3},
                                new int[]{3, 2}
                        }
                ),
                equalTo(true)
        );

        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{1, 1},
                                new int[]{2, 2},
                                new int[]{3, 3}
                        }
                ),
                equalTo(false)
        );

        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{1, 0},
                                new int[]{1, 1},
                                new int[]{1, 2}
                        }
                ),
                equalTo(false)
        );

        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{0, 0},
                                new int[]{1, 1},
                                new int[]{1, 1}
                        }
                ),
                equalTo(false)
        );

        assertThat(
                isBoomerang(
                        new int[][]{
                                new int[]{0, 0},
                                new int[]{0, 2},
                                new int[]{2, 1}
                        }
                ),
                equalTo(true)
        );
    }

    public boolean isBoomerang(int[][] points) {

        int[] p1 = points[0];
        int[] p2 = points[1];
        int[] p3 = points[2];

        if (!uniquePoints(p1, p2, p3)) return false;

        return (p2[1] - p1[1]) * (p3[0] - p2[0]) != (p3[1] - p2[1]) * (p2[0] - p1[0]);
    }

    private boolean uniquePoints(int[] p1, int[] p2, int[] p3) {
        boolean p1p2 = equalPoints(p1, p2);
        boolean p1p3 = equalPoints(p1, p3);
        boolean p2p3 = equalPoints(p2, p3);

        return !p1p2 && !p1p3 && !p2p3;
    }

    private boolean equalPoints(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }

}
