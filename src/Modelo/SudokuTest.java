package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {

	@Test
	public void testConstructor() {
		Sudoku sudoku = new Sudoku();
	}
	
	@Test
	public void getMatrizJuegoTest() {
		Sudoku sudoku = new Sudoku();
		Matriz matriz = sudoku.getMatrizJuego();
	}

}
