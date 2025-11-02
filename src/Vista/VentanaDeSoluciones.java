package Vista;

import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Sudoku;

import java.awt.Color;

public class VentanaDeSoluciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private Sudoku sudoku;
	private Controlador controlador;

	/**
	 * Create the panel.
	 */
	public VentanaDeSoluciones(Controlador controlador, Sudoku sudokuModelo, Tablero tablero) {
		setBounds(100, 100, 970, 622);
		setVisible(true);
		this.sudoku = sudokuModelo;
		this.controlador = controlador;
		
		
		
	}

}
