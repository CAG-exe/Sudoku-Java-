package Controlador;

import Modelo.Sudoku;
import Vista.InterfazFrame;

public class Controlador {
	
	private Sudoku sudokuModelo;
	private InterfazFrame interfazFrame;
	
	
	public Controlador() {
	}
	
	public void setSudoku(Sudoku sudoku) {
        this.sudokuModelo = sudoku;
    }

	public void setInterfazFrame(InterfazFrame interfazFrame) {
		this.interfazFrame = interfazFrame;
	}
	
	public void actualizarValorDeCelda(int fila, int columna, int valor) {
		sudokuModelo.actualizarValorDeLaCelda(fila, columna, valor);
	}
	
}
