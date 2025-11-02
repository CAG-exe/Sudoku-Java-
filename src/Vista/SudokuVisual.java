package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Sudoku;
import java.awt.Color;

public class SudokuVisual extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private Sudoku sudokuModelo;
	private int cantidadValoresPrefijados;
	private Tablero tablero;
	/**
	 * Create the panel.
	 */
	public SudokuVisual(Controlador controlador, Sudoku sudokuModelo,int cantidadValoresPrefijados) {
		this.controlador = controlador;
		this.sudokuModelo = sudokuModelo;
		this.cantidadValoresPrefijados = cantidadValoresPrefijados;
		setBounds(100, 100, 800, 600);
		setBackground(new Color(240, 240, 240));
		iniciar();
		}
	
	public void iniciar() {
		setLayout(null);
		tablero = new Tablero(controlador, sudokuModelo, cantidadValoresPrefijados);
		tablero.setBounds(0, -12, 800, 505);
		this.add(tablero);
		
		JButton buscarSoluciones = new JButton("Buscar Soluciones");
		buscarSoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarVisorDeSoluciones(sudokuModelo, cantidadValoresPrefijados, tablero);
			}
		});
		buscarSoluciones.setBounds(42, 493, 153, 52);
		add(buscarSoluciones);
		
		JButton volverAlMenu = new JButton("Volver");
		volverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarMenu();
			}
		});
		volverAlMenu.setBounds(619, 493, 129, 52);
		add(volverAlMenu);
		System.out.println(cantidadValoresPrefijados);
		
		
	}
	
	public void actualizarCasillasMarcadas(int valor) {
		if (valor == 0) {
			cantidadValoresPrefijados--;
			return;
		}
		cantidadValoresPrefijados++;
	}
}
