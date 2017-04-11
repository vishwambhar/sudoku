package com.example.sample;

/**
 * <p>
 * This class provides methods to place a known-correct digit on the board, place a not-necessarily correct digit on
 * the board, and query a cell value and its attributes (such as color).
 * </p>
 *
 * <p>
 * This class does't perform serious input validations such as whether row or col index is valid. It is expected that
 * layer above this class will perform such input validations before calling APIs of this class.
 * </p>
 */
public class Sudoku {

    /** Stores size of Sudoku board. A Sudoku board has 9x9 cells. */
    private static final int SIZE = 9;

    /** Holds reference of Sudoku board. */
    private final Board board;

    public Sudoku() {
        board = new Board();
    }

    /**
     * Sets the specified cell on the board with the supplied digit. This method doesn't perform any safety check and
     * assumes the value is correct.
     *
     * @param row   Row index.
     * @param col   Col index.
     * @param digit Digit value.
     */
    public void setCell(int row, int col, int digit) {
        board.setCell(row, col, digit, Color.GREEN);
    }

    /**
     * This method returns a {@code Cell} instance that corresponds to the position on the board specified by the user.
     */
    public Board.Cell getCell(int row, int col) {
        return board.getCell(row, col);
    }

    /**
     * This method assigns a cell to the digit value supplied by the user.
     *
     * @param row   Row index.
     * @param col   Col index.
     * @param digit Digit value.
     */
    public void assignCell(int row, int col, int digit) {
        if (isSafe(row, col, digit)) {
            board.setCell(row, col, digit, Color.WHITE);
        } else {
            board.setCell(row, col, digit, Color.RED);
        }
    }

    private boolean isSafe(int row, int col, int digit) {
        return checkRow(row, digit) && checkCol(col, digit) && checkSubGrid(row - row % 3, col - col % 3, digit);
    }

    /** Checks if supplied digit is an acceptable value for the given row. */
    private boolean checkRow(int row, int digit) {
        for (int col = 0; col < SIZE; col++) {
            if (board.getCell(row, col).digit == digit) {
                return false;
            }
        }
        return true;
    }

    /** Checks if supplied digit is an acceptable value for the given column. */
    private boolean checkCol(int col, int digit) {
        for (int row = 0; row < SIZE; row++) {
            if (board.getCell(row, col).digit == digit) {
                return false;
            }
        }
        return true;
    }

    /** Checks if supplied digit is an acceptable value for the sub-grid. */
    private boolean checkSubGrid(int startRow, int startCol, int digit) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getCell(row + startRow, col + startCol).digit == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This class models Sudoku board that consists of 9 x 9 cells. Each cell of the board is an instance of {@code
     * Cell} class.
     */
    public static class Board {

        private final Cell[][] cells;

        private Board() {
            cells = new Cell[SIZE][SIZE]; /* Always initialize a 9x9 grid that acts as Sudoku board. */
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    cells[row][col] = new Cell(0, Color.BLACK);
                }
            }
        }

        private void setCellColor(int row, int col, Color color) {
            cells[row][col].color = color;
        }

        private void setCellDigit(int row, int col, int digit) {
            cells[row][col].digit = digit;
        }

        private void setCell(int row, int col, int digit, Color color) {
            setCellColor(row, col, color);
            setCellDigit(row, col, digit);
        }

        private Cell getCell(int row, int col) {
            return cells[row][col];
        }

        /**
         * This class models a cell of the sudoku board. It can hold all attributes specific to a cell. Currently
         * defined attributes of a cell are {@code digit} and {@code color}.
         *
         * A zero value against a cell refers to an un-initialized cell. An un-initialized cell is of black color.
         */
        public static class Cell {

            private int digit;
            private Color color;

            private Cell(int digit, Color color) {
                this.digit = digit;
                this.color = color;
            }

            public int getDigit() {
                return digit;
            }

            public Color getColor() {
                return color;
            }
        }
    }

    public enum Color {
        BLACK, GREEN, WHITE, RED;
    }
}
