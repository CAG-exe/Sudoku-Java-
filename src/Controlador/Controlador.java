package Controlador;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import Modelo.Sudoku;
import Vista.InterfazFrame;
import Vista.SudokuVisual;
import Vista.Tablero;
import Vista.VisorDeSoluciones;

public class Controlador {
	
	private Sudoku sudokuModelo;
	private InterfazFrame interfazFrame;
	private Tablero tableroActual;
	private int cantidadValoresPrefijados;
	private SudokuVisual sudokuVisual;
	
	
	public Controlador() {
	}
	
	public void setSudoku(Sudoku sudoku) {
        this.sudokuModelo = sudoku;
    }

	public void setInterfazFrame(InterfazFrame interfazFrame) {
		this.interfazFrame = interfazFrame;
	}
	
	public void setTablero(Tablero tablero) {
		this.tableroActual = tablero;
	}
	
	
	public void mostrarSudoku() {
		Sudoku sudokuModelo = new Sudoku();
		sudokuVisual =  new SudokuVisual(this, sudokuModelo, 0);
		interfazFrame.mostrarPanelSudoku(sudokuVisual);
	}
	
	public void mostrarSudokuAleatorio(int cantidadValoresPrefijados) {
		Sudoku sudokuModelo = new Sudoku();
		sudokuVisual =  new SudokuVisual(this, sudokuModelo, cantidadValoresPrefijados);
		interfazFrame.mostrarPanelSudoku(sudokuVisual);
	}
	
	public void mostrarMenu() {
		interfazFrame.mostrarPanelMenu();
	}
	
	public void mostrarVisorDeSoluciones(Sudoku sudokuModelo, int cantidadValoresPrefijados, Tablero tablero) {
		VisorDeSoluciones visor = new VisorDeSoluciones(this, sudokuModelo,	tablero);
		interfazFrame.mostrarPanelSoluciones(visor);
	}
	
	
	public void actualizarValorDeCeldaEnModelo(int fila, int columna, int valor) {
		sudokuModelo.setValorDeLaCelda(fila, columna, valor);
		sudokuModelo.estaCompleto();
		if(interfazFrame != null) 
			sudokuVisual.actualizarCasillasMarcadas(valor);
			sudokuModelo.aumetarCantidadDeNumerosIngresadosEnElTablero(valor);
		
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
		tableroActual.restablecerColorDeFondo();
	}

	private void colorearCeldasAfectadas(int fila, int col) {
		tableroActual.colorearCuadrante(fila, col);
		tableroActual.colorearFila(fila);
		tableroActual.colorearColumna(col);
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
	
	public void mostrarSolucionIndividualEnElTablero() {
		sudokuModelo.resolverSudoku();
	    tableroActual.actualizarTableroConLaSolucion(sudokuModelo.getUnicaSolucion());
	    tableroActual.bloquearEdicionDeCasillas();
	}
	
	public void mostrarSolucionIndividualEnElTablero(Tablero tabla, int[][] solucion) {
		tabla.actualizarTableroConLaSolucion(solucion);
		tabla.bloquearEdicionDeCasillas();
	}
	
}
