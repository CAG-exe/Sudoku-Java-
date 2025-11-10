package Modelo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolucionadorDeSudokuTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarUnaSolucionRecursivoMatrizNulaTest() {

		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarUnaSolucionRecursivo(null, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarUnaSolucionRecursivoFilaInvalidasTest() {

		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarUnaSolucionRecursivo(matriz, -1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarUnaSolucionRecursivoColumnaInvalidasTest() {

		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarUnaSolucionRecursivo(matriz, 0, 10);
	}

	@Test
	public void encontrarUnaSolucionRecursivoTest() {
		
		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] { 5, 3, 0, 0, 7, 0, 0, 0, 0, 6, 0, 0, 1, 9, 5, 0, 0,
			0, 0, 9, 8, 0, 0, 0, 0, 6, 0, 8, 0, 0, 0, 6, 0, 0, 0, 3, 4, 0, 0, 8, 0, 3, 0, 0, 1, 7, 0, 0, 0, 2, 0, 0,
			0, 6, 0, 6, 0, 0, 0, 0, 2, 8, 0, 0, 0, 0, 4, 1, 9, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 7, 9 }));
		matriz.marcarCasillasConNumerosValidos(20);
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);
		
		
		solucionador.encontrarUnaSolucionRecursivo(matriz, 0, 0);
		
		assertEquals(1, solucionesDelSudokuJuego.size());
	}
	
	
	@Test
	public void encontrarTodasLasSolucionesRecursivoTest() {
		
		
        Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] { 1,2,3,4,5,6,7,8,9,4,5,6,7,8,9}));
        matriz.marcarCasillasConNumerosValidos(15);
        List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
        SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);
        
        int esperado = 3;
        solucionador.encontrarTodasLasSolucionesRecursivo(matriz, 0, 0, esperado);
        
        assertEquals(esperado, solucionesDelSudokuJuego.size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarTodasLasSolucionesRecursivoMatrizNulaTest() {

		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarTodasLasSolucionesRecursivo(null, 0, 0, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarTodasLasSolucionesRecursivoFilaInvalidasTest() {

		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarTodasLasSolucionesRecursivo(matriz, -1, 0, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarTodasLasSolucionesRecursivoColumnaInvalidasTest() {

		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarTodasLasSolucionesRecursivo(matriz, 0, 10, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encontrarTodasLasSolucionesRecursivoMaxSolucionesInvalidasTest() {

		Matriz matriz = new Matriz(new GeneradorPrefijado(new int[] {}));
		List<Matriz> solucionesDelSudokuJuego = new ArrayList<>();
		SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);

		solucionador.encontrarTodasLasSolucionesRecursivo(matriz, 0, 0, -1);
	}
}
