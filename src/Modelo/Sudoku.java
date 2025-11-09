package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private Matriz sudokuJuego;
	private List<Matriz> solucionesDelSudokuJuego;
	private int maxSolucionesAEncontrar;
	private Generador generadorAleatorio;
	
	public Sudoku(Generador generadorAleatorio) {
		this.generadorAleatorio = generadorAleatorio;
		sudokuJuego = new Matriz(generadorAleatorio);
		solucionesDelSudokuJuego = new ArrayList<Matriz>();
	}
	
	public Matriz getMatrizJuego() {
		return sudokuJuego;
	}
	
	public int[][] getMatrizDeSudokuConValoresPrefijados() {
		return sudokuJuego.getMatrizConValoresPrefijados();
	}
	
	public void resolverSudoku() {
		 Matriz sudokuSolucion = new Matriz(generadorAleatorio);
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
		if (solucionesDelSudokuJuego.size() == 0) {
			throw new IllegalStateException("No hay soluciones para el Sudoku ingresado");
		}
		return solucionesDelSudokuJuego.get(0).clonar();
	}

	public List<Matriz> getSoluciones() {
		return solucionesDelSudokuJuego;
	}
	
	public void aumetarCantidadDeNumerosIngresadosEnElTablero(int valor) {
		sudokuJuego.aumetarCantidadDeNumerosIngresadosEnElTablero(valor);
		
	}

	public void reiniciarSudoku() {
		sudokuJuego.rellenarDeCeros();
		sudokuJuego.reiniciarCantidadDeNumerosIngresadosEnElTablero();
		solucionesDelSudokuJuego = new ArrayList<Matriz>();
		maxSolucionesAEncontrar = 0;
	}
	
	public void setMaxSolucionesAEncontrar(int maxSoluciones) {
		this.maxSolucionesAEncontrar = maxSoluciones;
	}
}
