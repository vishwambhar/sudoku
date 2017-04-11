package com.example.sample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuTest {

    @Test
    public void testInitialBoardSetup() throws Exception {
        Sudoku sudoku = new Sudoku();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals(Sudoku.Color.BLACK, sudoku.getCell(row, col).getColor());
                assertEquals(0, sudoku.getCell(row, col).getDigit());
            }
        }
    }

    @Test
    public void testSetCell() throws Exception {
        Sudoku sudoku = new Sudoku();
        sudoku.setCell(0, 0, 1);
        assertEquals(1, sudoku.getCell(0, 0).getDigit());
        assertEquals(Sudoku.Color.GREEN, sudoku.getCell(0, 0).getColor());
    }

    @Test
    public void testAssignCellNotObviouslyCorrectValue() throws Exception {
        Sudoku sudoku = new Sudoku();
        // Fill few cells with known-to-be-correct value.
        sudoku.setCell(0, 0, 1);
        sudoku.setCell(0, 1, 2);
        sudoku.setCell(0, 2, 3);
        sudoku.setCell(0, 3, 4);
        sudoku.setCell(0, 8, 8);

        // test user assign values.
        sudoku.assignCell(0, 4, 5);
        Sudoku.Board.Cell cell = sudoku.getCell(0, 4);
        assertEquals(5, cell.getDigit());
        assertEquals(Sudoku.Color.WHITE, cell.getColor());
    }

    @Test
    public void testAssignCellObviouslyInCorrectValueForGivenRow() throws Exception {
        Sudoku sudoku = new Sudoku();
        // Fill few cells with known-to-be-correct value.
        sudoku.setCell(0, 0, 1);
        sudoku.setCell(0, 1, 2);
        sudoku.setCell(0, 2, 3);
        sudoku.setCell(0, 3, 4);
        sudoku.setCell(0, 8, 8);

        // test user assign values.
        sudoku.assignCell(0, 4, 8);
        Sudoku.Board.Cell cell = sudoku.getCell(0, 4);
        assertEquals(8, cell.getDigit());
        assertEquals(Sudoku.Color.RED, cell.getColor());
    }

    @Test
    public void testAssignCellObviouslyInCorrectValueForGivenCol() throws Exception {
        Sudoku sudoku = new Sudoku();
        // Fill few cells with known-to-be-correct value.
        sudoku.setCell(0, 0, 1);
        sudoku.setCell(0, 1, 2);
        sudoku.setCell(0, 2, 3);

        // test user assign values.
        sudoku.assignCell(2, 0, 1);
        Sudoku.Board.Cell cell = sudoku.getCell(2, 0);
        assertEquals(Sudoku.Color.RED, cell.getColor());
        assertEquals(1, cell.getDigit());
    }

    @Test
    public void testAssignCellObviouslyInCorrectValueForGivenSubGrid() throws Exception {
        Sudoku sudoku = new Sudoku();
        // Fill few cells with known-to-be-correct value.
        sudoku.setCell(0, 0, 1);
        sudoku.setCell(0, 1, 2);
        sudoku.setCell(0, 2, 3);

        // test user assign values.
        sudoku.assignCell(2, 2, 1);
        Sudoku.Board.Cell cell = sudoku.getCell(2, 2);
        assertEquals(Sudoku.Color.RED, cell.getColor());
        assertEquals(1, cell.getDigit());
    }
}