package Controlador;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import Modelo.Sudoku;
import Vista.InterfazFrame;
import Vista.Tablero;

public class Controlador {
	
	private Sudoku sudokuModelo;
	private InterfazFrame interfazFrame;
	private Tablero tablero;
	
	
	public Controlador() {
	}
	
	public void setSudoku(Sudoku sudoku) {
        this.sudokuModelo = sudoku;
    }

	public void setInterfazFrame(InterfazFrame interfazFrame) {
		this.interfazFrame = interfazFrame;
	}
	
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public void actualizarValorDeCeldaEnModelo(int fila, int columna, int valor) {
		sudokuModelo.actualizarValorDeLaCelda(fila, columna, valor);
		sudokuModelo.estaCompleto();
	}
	
	public void celdaActualizada(JTextField jText, int fila, int col, KeyEvent e) {
		colorearCeldasAfectadas(fila, col);
		int key = e.getKeyChar();
		boolean numeros = key >= 48 && key <= 57;
		if (!seTocoLaTeclaBorrar(key) && (esUnNumeroInvalido(jText, key, numeros) || !this.sudokuModelo.esSeguro(fila,col,key-48))) {
			e.consume();
		} else {
			actualizarValorDeCeldaEnModelo(fila, col, seTocoLaTeclaBorrar(key) ? 0 : (key - 48));
		}
	}
	
	public void celdaSeleccionada(int fila, int col) {
		colorearCeldasAfectadas(fila, col);
	}
	
	public void celdaDeseleccionada() {
		tablero.restablecerColorDeFondo();
	}

	private void colorearCeldasAfectadas(int fila, int col) {
		tablero.colorearCuadrante(fila, col);
		tablero.colorearFila(fila);
		tablero.colorearColumna(col);
	}

	private boolean esUnNumeroInvalido(JTextField jText, int key, boolean numeros) {
		return (!numeros || jText.getText().trim().length() == 1)  || (elNumeroEsCero(key) && jText.getText().trim().length() == 0);
	}

	private boolean seTocoLaTeclaBorrar(int key) {
		return key == 8;
	}

	private boolean elNumeroEsCero(int key) {
		return key == 48;
	}
	
}
