package com.williamheng.leet.y2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SubrectangleQueriesProblem {

    @Test
    public void test() {
        SubrectangleQueries sq = new SubrectangleQueries(
                new int[][]{
                        new int[]{1, 2, 1},
                        new int[]{4, 3, 4},
                        new int[]{3, 2, 1},
                        new int[]{1, 1, 1},
                }
        );

        assertThat(sq.getValue(0, 2), equalTo(1));

        sq.updateSubrectangle(0, 0, 3, 2, 5);

        assertThat(sq.getValue(0, 2), equalTo(5));
        assertThat(sq.getValue(3, 1), equalTo(5));

        sq.updateSubrectangle(3, 0, 3, 2, 10);

        assertThat(sq.getValue(3, 1), equalTo(10));
        assertThat(sq.getValue(0, 2), equalTo(5));
    }

    @Test
    public void test2() {
        SubrectangleQueries sq = new SubrectangleQueries(
                new int[][]{
                        new int[]{1, 1, 1},
                        new int[]{2, 2, 2},
                        new int[]{3, 3, 3},
                }
        );

        assertThat(sq.getValue(0, 0), equalTo(1));

        sq.updateSubrectangle(0, 0, 2, 2, 100);

        assertThat(sq.getValue(0, 0), equalTo(100));
        assertThat(sq.getValue(2, 2), equalTo(100));

        sq.updateSubrectangle(1, 1, 2, 2, 20);

        assertThat(sq.getValue(2, 2), equalTo(20));
    }

    @Test
    public void test3() {
        SubrectangleQueries sq = new SubrectangleQueries(
                new int[][]{
                        new int[]{1, 1, 1},
                        new int[]{2, 2, 2},
                        new int[]{3, 3, 3},
                }
        );
        assertThat(sq.getValue(1, 1), equalTo(2));

        sq.updateSubrectangle(1, 1, 1, 1, 100);

        assertThat(sq.getValue(1, 1), equalTo(100));
    }

    class SubrectangleQueries {

        int[][] rectangle;

        public SubrectangleQueries(int[][] rectangle) {
            this.rectangle = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    rectangle[i][j] = newValue;
                }
            }
        }

        public int getValue(int row, int col) {
            return rectangle[row][col];
        }
    }

}
