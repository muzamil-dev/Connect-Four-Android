package com.example.connectfour;

public class ConnectFourGame {
    private int[][] board;

    public ConnectFourGame() {
        board = new int[7][6];
        newGame();
    }

    public void newGame() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
    }

    public String getState() {
        StringBuilder state = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                state.append(cell);
            }
        }
        return state.toString();
    }
}