package com.williamheng.leet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class MergeInterval {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[][]{}, new int[][]{}},
                {
                        new int[][]{
                                new int[]{1, 3},
                                new int[]{2, 6},
                                new int[]{8, 10},
                                new int[]{15, 18}
                        },
                        new int[][]{
                                new int[]{1, 6},
                                new int[]{8, 10},
                                new int[]{15, 18}
                        }
                },
                {
                        new int[][]{
                                new int[]{15, 18},
                                new int[]{2, 6},
                                new int[]{1, 3},
                                new int[]{8, 10}
                        },
                        new int[][]{
                                new int[]{1, 6},
                                new int[]{8, 10},
                                new int[]{15, 18}
                        }
                },
                {
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{4, 5}
                        },
                        new int[][]{
                                new int[]{1, 5}
                        }
                },
                {
                        new int[][]{
                                new int[]{4, 5},
                                new int[]{1, 4}
                        },
                        new int[][]{
                                new int[]{1, 5}
                        }
                },
                {
                        new int[][]{
                                new int[]{1, 4},
                                new int[]{2, 3}
                        },
                        new int[][]{
                                new int[]{1, 4}
                        }
                }
        });
    }

    @Parameter(0)
    public int[][] intervals;

    @Parameter(1)
    public int[][] expected;

    @Test
    public void test() {
        assertThat(merge(intervals), equalTo(expected));
    }


    public int[][] merge(int[][] intervals) {

        // First sort by first element
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                int[] current = intervals[i];
                int[] next = intervals[j];

                if (next[0] < current[0]) {
                    // swap
                    int[] temp = intervals[i];
                    intervals[i] = intervals[j];
                    intervals[j] = temp;
                }
            }
        }

        print(intervals);

        // Then merge
        List<int[]> results = new ArrayList<>();

        for (int i = 0; i < intervals.length; ) {
            int[] current = intervals[i];

            if (current.length == 2) {
                int min = current[0];
                int max = current[1];

                if (i == intervals.length - 1) {
                    results.add(new int[]{min, max});
                    break;
                }

                int j = i + 1;
                for (; j < intervals.length; j++) {
                    int[] next = intervals[j];

                    if (max >= next[0] && max <= next[1]) {
                        // current interval falls in the next interval
                        max = next[1];
                    } else if (max >= next[0] && max >= next[1]) {
                        // ignoring
                        continue;
                    } else {
                        break;
                    }
                }

                results.add(new int[]{min, max});
                i = j;
            }
        }

        return convert(results);
    }

    int[][] convert(List<int[]> x) {
        int[][] results = new int[x.size()][];
        for (int i = 0; i < x.size(); i++) {
            results[i] = x.get(i);
        }
        return results;
    }

    void print(int[][] intervals) {
        System.out.print("[ ");
        for (int[] interval : intervals) {
            if (interval.length > 0) {
                System.out.print(String.format("(%d, %d) ", interval[0], interval[1]));
            }
        }
        System.out.print("]");
        System.out.println();
    }

}
