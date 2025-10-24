package Controlador;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

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
	
	public void actualizarValorDeCeldaEnModelo(int fila, int columna, int valor) {
		sudokuModelo.actualizarValorDeLaCelda(fila, columna, valor);
		sudokuModelo.estaCompleto();
	}
	
	public void celdaActualizada(JTextField jText, int fila, int col, KeyEvent e) {
		int key = e.getKeyChar();
		boolean numeros = key >= 48 && key <= 57;
		if (!jText.getText().isEmpty() || ((!numeros || jText.getText().trim().length() == 1) || (key == 48 && jText.getText().trim().length() == 0) || !this.sudokuModelo.esSeguro(fila,col,key-48))) {
			e.consume();
		} else {
			actualizarValorDeCeldaEnModelo(fila, col, key == 8 ? 0 : (key - 48));
		}
	}
	
}
