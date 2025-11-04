package Modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {

	@Test
	public void testConstructor() {
		@SuppressWarnings("unused")
		Sudoku sudoku = new Sudoku();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void getMatrizJuegoTest() {
		Sudoku sudoku = new Sudoku();
		Matriz matriz = sudoku.getMatrizJuego();
	}
	
	
	private void testMode(Matriz m) {
		int[][] Mtest =  new int[][]{
			{5, 3, 0, 0, 7, 0, 0, 0, 0},
		    {6, 0, 0, 1, 9, 5, 0, 0, 0},
		    {0, 9, 8, 0, 0, 0, 0, 6, 0},
		    {8, 0, 0, 0, 6, 0, 0, 0, 3},
		    {4, 0, 0, 8, 0, 3, 0, 0, 1},
		    {7, 0, 0, 0, 2, 0, 0, 0, 6},
		    {0, 6, 0, 0, 0, 0, 2, 8, 0},
		    {0, 0, 0, 4, 1, 9, 0, 0, 5},
		    {0, 0, 0, 0, 8, 0, 0, 7, 9}};
		 m.setMatrizClonada(Mtest);
	}

	
	@Test
	public void esSeguroTest() {
		Sudoku sudoku = new Sudoku();
		Matriz m = sudoku.getMatrizJuego();
		testMode(m);
		assertTrue(sudoku.esSeguro(0, 2, 4));
		assertFalse(sudoku.esSeguro(0, 1, 3));
	}
	
	@Test
	public void actualizarMatrizJuegoTest() {
		Sudoku sudoku = new Sudoku();
		Matriz m = sudoku.getMatrizJuego();
		testMode(m);
		sudoku.setValorDeLaCelda(0, 2, 4);
		assertEquals(4, m.getValorEnMatriz(0,2));
	}
	@Test(expected = IllegalStateException.class)
    public void testGetUnicaSolucion_SinSoluciones() {
        Sudoku sudoku = new Sudoku();
        sudoku.getUnicaSolucion(); 
    }
}
