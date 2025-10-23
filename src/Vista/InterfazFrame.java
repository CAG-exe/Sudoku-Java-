package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controlador.Controlador;
import Modelo.Sudoku;

public class InterfazFrame {

	private JFrame frame;
	private Tablero tablero;
	private Controlador controlador;
	private Sudoku sudokuModelo;
	
	public static void main(String[] args) {
		
	}

	public InterfazFrame(Controlador controlador, Sudoku sudokuModelo) {
		this.controlador = controlador;
		this.sudokuModelo = sudokuModelo;
		initialize();
	}
	
	private void initialize() {
		frame.setVisible(true);
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		/// para que muestre el tablero de una
		tablero = new Tablero(controlador, sudokuModelo);
		frame.setContentPane(tablero);
		
		JButton generaSudoku = new JButton("Generar Sudoku");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 14));
		generaSudoku.setBounds(600, 497, 150, 50);
		frame.add(generaSudoku);
		generaSudoku.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = JOptionPane.showInputDialog(
					null,
					"Ingrese una cantidad de valores prefijados:",
					"",
					JOptionPane.CANCEL_OPTION
				);
				if (texto != null) {
					int cantidad = Integer.parseInt(texto.trim());
					if (cantidad >= 1 && cantidad <= 81) {
						frame.remove(tablero);
						tablero = new Tablero(controlador, sudokuModelo);
					}
					else {
						JOptionPane.showMessageDialog(frame, 
							"Entre 1 y 81.", 
							"", 
							JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
	});
}
}
