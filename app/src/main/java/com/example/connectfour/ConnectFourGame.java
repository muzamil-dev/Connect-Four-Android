package com.example.connectfour;

public class ConnectFourGame {
    // Add constants
    public static final int ROW = 7;
    public static final int COL = 6;
    public static final int EMPTY = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;
    public static final int DISCS = 42;

    // Add member variable
    private int player = BLUE;
    private int[][] board;

    // Constructor
    public ConnectFourGame() {
        board = new int[ROW][COL];
        newGame();
    }

    // Step 3: Update method newGame
    public void newGame() {
        player = BLUE;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    // Step 4: Write method getDisc
    public int getDisc(int row, int col) {
        return board[row][col];
    }

    // Step 5: Write method isGameOver
    public boolean isGameOver() {
        return isFull() || isWin();
    }

    // Helper method for isGameOver: Check if the board is full
    private boolean isFull() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Step 6: Write method isWin
    public boolean isWin() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    // Step 7: Write method checkHorizontal
    private boolean checkHorizontal() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col <= COL - 4; col++) {
                int disc = board[row][col];
                if (disc != EMPTY && disc == board[row][col + 1] && disc == board[row][col + 2] && disc == board[row][col + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Step 8: Write method checkVertical
    private boolean checkVertical() {
        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = 0; col < COL; col++) {
                int disc = board[row][col];
                if (disc != EMPTY && disc == board[row + 1][col] && disc == board[row + 2][col] && disc == board[row + 3][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Step 9: Write method checkDiagonal
    private boolean checkDiagonal() {
        // Check diagonals from top-left to bottom-right
        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = 0; col <= COL - 4; col++) {
                int disc = board[row][col];
                if (disc != EMPTY && disc == board[row + 1][col + 1] && disc == board[row + 2][col + 2] && disc == board[row + 3][col + 3]) {
                    return true;
                }
            }
        }
        // Check diagonals from top-right to bottom-left
        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = 3; col < COL; col++) {
                int disc = board[row][col];
                if (disc != EMPTY && disc == board[row + 1][col - 1] && disc == board[row + 2][col - 2] && disc == board[row + 3][col - 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Step 10: Write method setState
    public void setState(String gameState) {
        int index = 0;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = Character.getNumericValue(gameState.charAt(index++));
            }
        }
    }

    // Step 11: Write method selectDisc
    public void selectDisc(int row, int col) {
        for (int r = ROW - 1; r >= 0; r--) {
            if (board[r][col] == EMPTY) {
                board[r][col] = player;
                player = (player == BLUE) ? RED : BLUE;
                break;
            }
        }
    }

    // Step 12: Write method getState
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
