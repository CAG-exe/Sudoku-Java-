package Modelo;

import java.util.List;

public class SolucionadorDeSudoku {
	private List<Matriz> solucionesDelSudokuJuego;
	
	public SolucionadorDeSudoku(List<Matriz> solucionesDelSudokuJuego) {
		this.solucionesDelSudokuJuego = solucionesDelSudokuJuego;
	}
	
	boolean encontrarUnaSolucionRecursivo(Matriz sudokuSolucion, int fila, int columna) {
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
	
	void encontrarTodasLasSolucionesRecursivo(Matriz sudokuSolucion, int fila, int columna, int maxSolucionesAEncontrar) {
		
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
			encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1, maxSolucionesAEncontrar);
		} else {
			for(int i = 1; i<10; i++) {
				if(sudokuSolucion.esSeguro(fila, columna, i)) {
					sudokuSolucion.setMatriz(fila, columna, i);
					encontrarTodasLasSolucionesRecursivo(sudokuSolucion, fila, columna + 1, maxSolucionesAEncontrar);
					
					sudokuSolucion.setMatriz(fila, columna, 0);
				}
				
			}
		}
	}
}
