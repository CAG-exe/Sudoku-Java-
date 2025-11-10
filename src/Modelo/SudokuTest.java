package Modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest {

	private Sudoku sudoku;


	@Before
	public void testConstructor() {
		this.sudoku = new Sudoku(new GeneradorPrefijado(new int[] {}));
	}
	
	@Test
	public void getMatrizJuegoTest() {
		Matriz matriz = sudoku.getMatrizJuego();
		
		assertNotNull(matriz);
	}
	
	
	@Test
	public void setValorDeLaCeldaTest() {
		sudoku.setValorDeLaCelda(0, 0, 5);
		Matriz m = sudoku.getMatrizJuego();
		
		assertEquals(5, m.getValorEnMatriz(0, 0));
	}
	
	
	@Test
	public void esSeguroTest() {
		
		sudoku.setValorDeLaCelda(0, 1, 3);
		
		assertTrue(sudoku.esSeguro(0, 2, 4));
		assertFalse(sudoku.esSeguro(0, 1, 3));
	}
	
	@Test
	public void reiniciarSudokuTest() {
		sudoku.setValorDeLaCelda(0, 0, 5);
		
		sudoku.reiniciarSudoku();
		
		Matriz m = sudoku.getMatrizJuego();
		assertEquals(0, m.getValorEnMatriz(0, 0));
	}
	
	@Test(expected = IllegalStateException.class)
    public void testGetUnicaSolucion_SinSoluciones() {
        sudoku.getUnicaSolucion(); 
    }
	
	
	@Test
	public void testGetUnicaSolucion_ConSolucion() {
		Sudoku sudoku = new Sudoku(new GeneradorPrefijado(new int[] { 5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0,
				0, 0, 9, 8, 0, 0, 0, 0, 6, 0, 8, 0, 0, 0, 6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1, 7, 0, 0, 0, 2, 0, 0,
				0, 6, 0, 6, 0, 0, 0, 0, 2, 8, 0, 0, 0, 0, 4, 1, 9, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 7, 9 }));
		
		Matriz m = sudoku.getMatrizJuego();
		m.marcarCasillasConNumerosValidos(30);
		
		sudoku.resolverSudoku();
		int[][] solucion = sudoku.getUnicaSolucion();

		assertNotNull(solucion);
		
		assertArrayEquals(new int[] { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, solucion[0]);
	}
	
	
	@Test
	public void aumetarCantidadDeNumerosIngresadosEnElTableroTest() {
		
		this.sudoku.aumetarCantidadDeNumerosIngresadosEnElTablero(1);
		Matriz m = sudoku.getMatrizJuego();
		
		assertEquals(1, m.getCantDeValoresPrefijados());
		
		this.sudoku.aumetarCantidadDeNumerosIngresadosEnElTablero(1);
		
		assertNotEquals(1, m.getCantDeValoresPrefijados());
	}
	
	@Test
	public void testGetSoluciones() {
		Sudoku sudoku = new Sudoku(new GeneradorPrefijado(new int[] { 5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0,
				0, 0, 9, 8, 0, 0, 0, 0, 6, 0, 8, 0, 0, 0, 6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1, 7, 0, 0, 0, 2, 0, 0,
				0, 6, 0, 6, 0, 0, 0, 0, 2, 8, 0, 0, 0, 0, 4, 1, 9, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 7, 9 }));

		Matriz m = sudoku.getMatrizJuego();
		m.marcarCasillasConNumerosValidos(30);

		sudoku.resolverSudoku();
		List<Matriz> soluciones = sudoku.getSoluciones();

		assertNotNull(soluciones);
		assertFalse(soluciones.isEmpty());
	}
	
	
	@Test
	public void resolverSudokuConMuchasSoluciones() {
		Sudoku sudoku = new Sudoku(new GeneradorPrefijado(new int[] { 1,2,3,4,5,6,7,8,9,4,5,6,7,8,9}));
		
		Matriz m = sudoku.getMatrizJuego();
		sudoku.setMaxSolucionesAEncontrar(2);
		m.marcarCasillasConNumerosValidos(15);
		
		sudoku.resolverSudoku();
		List<Matriz> soluciones = sudoku.getSoluciones();
		
		assertNotNull(soluciones);
		assertFalse(soluciones.isEmpty());
		assertTrue(soluciones.size() == 2);
	}

}
