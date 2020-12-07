package com.williamheng.leet.y2020;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SetMatrixZeroes {

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                new int[]{1, 1, 1},
                new int[]{1, 0, 1},
                new int[]{1, 1, 1}
        };
        setZeroes(matrix);
        assertArrayEquals(
                matrix,
                new int[][]{
                        new int[]{1, 0, 1},
                        new int[]{0, 0, 0},
                        new int[]{1, 0, 1}
                }
        );
    }

    @Test
    public void test2() {
        int[][] matrix = new int[][]{
                new int[]{0, 1, 2, 0},
                new int[]{3, 4, 5, 2},
                new int[]{1, 3, 1, 5}
        };
        setZeroes(matrix);
        assertArrayEquals(
                matrix,
                new int[][]{
                        new int[]{0, 0, 0, 0},
                        new int[]{0, 4, 5, 0},
                        new int[]{0, 3, 1, 0},
                }
        );
    }

    public void setZeroes(int[][] matrix) {

        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    rows[row] = 1;
                    cols[col] = 1;
                }
            }
        }

        for (int row = 0; row < rows.length; row++) {
            if (rows[row] == 1) {
                for (int col = 0; col < matrix[row].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        for (int col = 0; col < cols.length; col++) {
            if (cols[col] == 1) {
                for (int row = 0; row < rows.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        }

    }

}
