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
	public void resolverSudokuTest() {
		Sudoku sudoku = new Sudoku();
		Matriz matriz = sudoku.getMatrizJuego();
		testMode(matriz);
		int[][] tableroResuelto = {
			    {5, 3, 4, 6, 7, 8, 9, 1, 2},
			    {6, 7, 2, 1, 9, 5, 3, 4, 8},
			    {1, 9, 8, 3, 4, 2, 5, 6, 7},
			    {8, 5, 9, 7, 6, 1, 4, 2, 3},
			    {4, 2, 6, 8, 5, 3, 7, 9, 1},
			    {7, 1, 3, 9, 2, 4, 8, 5, 6},
			    {9, 6, 1, 5, 3, 7, 2, 8, 4},
			    {2, 8, 7, 4, 1, 9, 6, 3, 5},
			    {3, 4, 5, 2, 8, 6, 1, 7, 9}
			};
		matriz.setCantDeValoresPrefijados(30);
		boolean resuelto = sudoku.resolverSudoku();
		assertTrue(resuelto);
		assertArrayEquals(tableroResuelto, sudoku.getUnicaSolucion());
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
	@Test(expected = IndexOutOfBoundsException.class)
    public void testGetUnicaSolucion_SinSoluciones() {
        Sudoku sudoku = new Sudoku();
        sudoku.getUnicaSolucion(); 
    }
}
