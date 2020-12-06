package com.williamheng.leet.y2020;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NQueen {
    @Test
    public void test() {
        assertThat(solveNQueens(4), equalTo(
                Arrays.asList(
                        Arrays.asList(
                                ".Q..",
                                "...Q",
                                "Q...",
                                "..Q."
                        ),
                        Arrays.asList(
                                "..Q.",
                                "Q...",
                                "...Q",
                                ".Q.."
                        )
                )
        ));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        bt(results, new char[n][n], 0, 0, n, n);
        return results;
    }

    void bt(List<List<String>> results, char[][] board, int row, int col, int n, int qRemaining) {
        if (qRemaining == 0) {
            results.add(serializeBoard(board));
            return;
        }

        // no places available in board, return
        if (row == n || col == n) return;

        if (board[row][col] == '.') {
            bt(results, board, row, col + 1, n, qRemaining);
            return;
        }

        // Consider current as Q
        char[][] currentBoard = copyBoard(board);
        currentBoard[row][col] = 'Q';
        markRow(currentBoard, row, col);
        markColumn(currentBoard, row, col);
        markDiagonal(currentBoard, row, col);
        bt(results, currentBoard, row + 1, 0, n, qRemaining - 1);


        // Consider the next column with the current as .
        currentBoard = copyBoard(board);
        currentBoard[row][col] = '.';
        bt(results, currentBoard, row, col + 1, n, qRemaining);
    }

    char[][] copyBoard(char[][] board) {
        int n = board.length;
        char[][] copy = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    void markRow(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (col != i) {
                board[row][i] = '.';
            }
        }
    }

    void markColumn(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (row != i) {
                board[i][col] = '.';
            }
        }
    }

    void markDiagonal(char[][] board, int row, int col) {
        int r = row + 1;
        int c = col + 1;
        while (r < board.length && c < board.length) {
            board[r][c] = '.';
            r++;
            c++;
        }

        r = row + 1;
        c = col - 1;
        while (r < board.length && c >= 0) {
            board[r][c] = '.';
            r++;
            c--;
        }
    }

    List<String> serializeBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] chars : board) {
            result.add(String.valueOf(chars));
        }
        return result;
    }
}
