package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private Matriz sudokuJuego;
	private List<Matriz> solucionesDelSudokuJuego;
	private int maxSolucionesAEncontrar;
	
	public Sudoku() {
		sudokuJuego = new Matriz();
		solucionesDelSudokuJuego = new ArrayList<Matriz>();
	}
	
	public Matriz getMatrizJuego() {
		return sudokuJuego;
	}
	
	public int[][] getMatrizDeSudokuConValoresPrefijados() {
		return sudokuJuego.getMatrizConValoresPrefijados();
	}
	
	public void resolverSudoku() {
		 Matriz sudokuSolucion = new Matriz();
		 int[][] matrizSudokuClonada = sudokuJuego.clonar();
		 sudokuSolucion.setMatrizClonada(matrizSudokuClonada);
		 int fila = 0;
		 int columna = 0;
		 SolucionadorDeSudoku solucionador = new SolucionadorDeSudoku(solucionesDelSudokuJuego);
		 if(sudokuJuego.getCantDeValoresPrefijados() >=17) {
			 	solucionador.encontrarUnaSolucionRecursivo(sudokuSolucion, fila, columna);
			} else {
				solucionador.encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna, maxSolucionesAEncontrar);
		 }
	
	 }


	public void setValorDeLaCelda(int fila, int columna, int valor) {
		sudokuJuego.setMatriz(fila, columna, valor);
	}
	
	public boolean esSeguro(int fila, int columna, int valor) {
		return sudokuJuego.esSeguro(fila, columna, valor);
	}

	public int[][] getUnicaSolucion() {
		try {
			return solucionesDelSudokuJuego.get(0).clonar();
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalStateException("No hay soluciones disponibles.");
		}
	}

	public List<Matriz> getSoluciones() {
		return solucionesDelSudokuJuego;
	}
	
	public void aumetarCantidadDeNumerosIngresadosEnElTablero(int valor) {
		sudokuJuego.aumetarCantidadDeNumerosIngresadosEnElTablero(valor);
		
	}

	public void reiniciarSudoku() {
		sudokuJuego.rellenarDeCeros();
	}
	
	public void setMaxSolucionesAEncontrar(int maxSoluciones) {
		this.maxSolucionesAEncontrar = maxSoluciones;
	}
}
