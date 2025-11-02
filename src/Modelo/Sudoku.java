package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private Matriz sudokuJuego;
	private List<Matriz> solucionesDelSudokuJuego;
	private int maxSolucionesAEncontrar = 1000;
	
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
	
	public boolean resolverSudoku() {
		 Matriz sudokuSolucion = new Matriz();
		 int[][] matrizSudokuClonada = sudokuJuego.clonar();
		 sudokuSolucion.setMatrizClonada(matrizSudokuClonada);
		 int fila = 0;
		 int columna = 0;
		 if(sudokuJuego.getCantDeValoresPrefijados() >=17) {
				return encontrarUnaSolucionRecursivo(sudokuSolucion, fila, columna);
			} else {
				encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna);
				return solucionesDelSudokuJuego.size() > 0;
		 }
	
	 }

	private boolean encontrarUnaSolucionRecursivo(Matriz sudokuSolucion, int fila, int columna) {
		if(sudokuSolucion.matrizCompleta()) {
			Matriz copiaSolucion = new Matriz();
			int[][] matrizSudokuClonada = sudokuSolucion.clonar();
			copiaSolucion.setMatrizClonada(matrizSudokuClonada);
			solucionesDelSudokuJuego.add(copiaSolucion);
			return true;
		}
		
		if(columna == 9) {
			fila += 1 ;
			columna = 0;
		}
		
		if(sudokuSolucion.casillaMarcada(fila, columna)) {
			return encontrarUnaSolucionRecursivo(sudokuSolucion, fila, columna + 1);
		}
		
		for(int i = 1; i<10; i++) {
			if(sudokuSolucion.esSeguro(fila, columna, i)) {
				sudokuSolucion.setMatriz(fila, columna, i);
				if(encontrarUnaSolucionRecursivo(sudokuSolucion, fila, columna + 1)) {
					return true;
				}
				
				sudokuSolucion.setMatriz(fila, columna, 0);
			}
			
		}
		
		return false;
	}
	
	private void encontrarTodasLasSolucionesRecursivo(Matriz sudokuSolucion, int fila, int columna) {
		
		if(solucionesDelSudokuJuego.size() == maxSolucionesAEncontrar){
			return;
		}
		if(columna == 9) {
			fila += 1 ;
			columna = 0;
			
		}
		
		if(sudokuSolucion.matrizCompleta()) {
			Matriz copiaSolucion = new Matriz();
			int[][] matrizSudokuClonada = sudokuSolucion.clonar();
			copiaSolucion.setMatrizClonada(matrizSudokuClonada);
			solucionesDelSudokuJuego.add(copiaSolucion);
			return;
		} 
		
		if(sudokuSolucion.casillaMarcada(fila, columna)) {
			encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1);
		} else {
			for(int i = 1; i<10; i++) {
				if(sudokuSolucion.esSeguro(fila, columna, i)) {
					sudokuSolucion.setMatriz(fila, columna, i);
					encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1);
					
					sudokuSolucion.setMatriz(fila, columna, 0);
				}
				
			}
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

	public void estaCompleto() {
		
	}

	public List<Matriz> getSoluciones() {
		return solucionesDelSudokuJuego;
	}
	
	public void aumetarCantidadDeNumerosIngresadosEnElTablero(int valor) {
		sudokuJuego.aumetarCantidadDeNumerosIngresadosEnElTablero(valor);
		
	}
}
