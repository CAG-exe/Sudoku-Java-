package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Sudoku;
import java.awt.Color;
import java.awt.Component;

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
		tablero.setBounds(0, 11, 800, 479);
		this.add(tablero);
		
		JButton buscarSoluciones = new JButton("Buscar Soluciones");
		buscarSoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.buscarSoluciones(sudokuModelo, cantidadValoresPrefijados, tablero);
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
		volverAlMenu.setBounds(612, 493, 153, 52);
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
	
	public void bloquearEdicionDeCasillas() {
		tablero.bloquearEdicionDeCasillas();
	}
	
	
	public int preguntarCantidadDeSoluciones() {
		int cantidad = 1;
		String texto = JOptionPane.showInputDialog(
				null,
				"Como hay menos de 17 valores prefijados, pueden existir múltiples soluciones.\n Ingrese la cantidad de soluciones a buscar:",
				"",
				JOptionPane.CANCEL_OPTION
			);
			if (texto != null) {
				cantidad = Integer.parseInt(texto.trim());
				if (cantidad > 0) {
					return cantidad;
				}
				else {
					Component frame = null;
					JOptionPane.showMessageDialog(frame, 
							"Tiene que ser un numero mayor a 0", 
							"", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		return -1;
	}

	public void mostrarMensajeDeSudokuSinSoluciones() {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, "No se encontró ninguna solución para el Sudoku ingresado.", "", JOptionPane.ERROR_MESSAGE);
	}
}
