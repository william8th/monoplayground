package com.williamheng.leet.y2020;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class KnightProbability {

    @Test
    public void test() {
        assertThat(knightProbability(3, 2, 0, 0), equalTo(0.0625));
        assertThat(knightProbability(3, 3, 0, 0), equalTo(0.015625));
    }

    int[] dx = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    int[] dy = new int[]{2, 1, -1, -2, -2, -1, 1, 2};

    public double knightProbability(int N, int K, int r, int c) {
        double[][] sp = new double[N][N];
        sp[r][c] = 1;

        for (; K > 0; K--) {
            double[][] p = new double[N][N];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    for (int i = 0; i < dx.length; i++) {
                        int newX = x + dx[i];
                        int newY = y + dy[i];
                        if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                            p[newX][newY] += sp[x][y] / 8.0;
                        }
                    }
                }
            }
            sp = p;
        }

        // Sum resulting probability
        double result = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += sp[i][j];
            }
        }

        return result;
    }

}
