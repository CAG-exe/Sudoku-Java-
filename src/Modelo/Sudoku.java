package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private Matriz sudokuJuego;
	private List<Matriz> solucionesDelSudokuJuego;
	
	
	public Sudoku() {
		sudokuJuego = new Matriz();
		solucionesDelSudokuJuego = new ArrayList<Matriz>();
	}
	
	public Matriz getMatrizJuego() {
		return sudokuJuego;
	}
	
	public boolean resolverSudoku() {
		 Matriz sudokuSolucion = new Matriz();
		 int[][] matrizSudokuClonada = sudokuJuego.clonar();
		 sudokuSolucion.setMatrizClonada(matrizSudokuClonada);
		 int fila = 0;
		 int columna = 0;
		 return encontrarTodasLasSolucionesRecursivo(sudokuSolucion,fila,columna);
	 }

	private boolean encontrarTodasLasSolucionesRecursivo(Matriz sudokuSolucion, int fila, int columna) {
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
			return encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1);
		}
		
		for(int i = 1; i<10; i++) {
			if(sudokuSolucion.esSeguro(fila, columna, i)) {
				sudokuSolucion.setMatriz(fila, columna, i);
				if(encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1)) {
					return true;
				}
				
				sudokuSolucion.setMatriz(fila, columna, 0);
			}
			
		}
		
		return false;
	}

	public void actualizarValorDeLaCelda(int fila, int columna, int valor) {
		sudokuJuego.setMatriz(fila, columna, valor);
	}
}
