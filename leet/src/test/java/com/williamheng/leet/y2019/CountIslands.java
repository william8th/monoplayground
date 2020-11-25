package com.williamheng.leet.y2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountIslands {

    @Test
    public void test() {
        // 11110
        // 11010
        // 11000
        // 00000
        char[][] map = new char[][] {
                new char[]{ '1', '1', '1', '1', '0' },
                new char[]{ '1', '1', '0', '1', '0' },
                new char[]{ '1', '1', '0', '0', '0' },
                new char[]{ '0', '0', '0', '0', '0' },
        };
        assertThat(numIslands(map), equalTo(1));

        //11000
        //11000
        //00100
        //00011
        char[][] map2 = new char[][] {
                new char[]{ '1', '1', '0', '0', '0' },
                new char[]{ '1', '1', '0', '0', '0' },
                new char[]{ '0', '0', '1', '0', '0' },
                new char[]{ '0', '0', '0', '1', '1' },
        };
        assertThat(numIslands(map2), equalTo(3));
    }

    public int numIslands(char[][] grid) {

        if (grid.length == 0) return 0;

        int rowSize = grid.length;
        int colSize = grid[0].length;
        int[][] visited = new int[rowSize][colSize];
        int count = 0;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {

                if (grid[row][col] == '1' && visited[row][col] != 1) {
                    DFS(grid, row, col, visited);
                    count++;
                }

            }
        }

        return count;
    }

    private void DFS(char[][] grid, int row, int col, int[][] visited) {

        visited[row][col] = 1;

        int rowSize = grid.length;
        int colSize = grid[0].length;

        // 8-neighbours index delta
//        int[] rowDelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
//        int[] colDelta = { -1, 0, 1, -1, 1, -1, 0, 1 };

        // 4-neighbours index delta
        int[] rowDelta = { -1, 0, 0, 1 };
        int[] colDelta = { 0, -1, 1, 0 };

        for (int k = 0; k < rowDelta.length; k++) {
            int i = row + rowDelta[k];
            int j = col + colDelta[k];

            if (
                    i >= 0 && i < rowSize &&
                            j >= 0 && j < colSize &&
                            grid[i][j] == '1' &&
                            visited[i][j] != 1
            ) {
                DFS(grid, i, j, visited);
            }
        }

    }
}
