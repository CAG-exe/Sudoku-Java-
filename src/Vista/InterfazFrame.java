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
	private int cantidadValoresPrefijados;
	

	public InterfazFrame(Controlador controlador, Sudoku sudokuModelo,int cantidadValoresPrefijados) {
		this.controlador = controlador;
		this.sudokuModelo = sudokuModelo;
		this.cantidadValoresPrefijados = cantidadValoresPrefijados;
		controlador.setSudoku(sudokuModelo);
		inicializar();
	}
	
	private void inicializar() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		/// para que muestre el tablero de una
		tablero = new Tablero(controlador, sudokuModelo, cantidadValoresPrefijados);
		frame.setContentPane(tablero);
		
		JButton solucionComprobar = new JButton("Comprobar solución");
		solucionComprobar.setFocusable(false);
		solucionComprobar.setFont(new Font("Arial", Font.BOLD, 14));
		solucionComprobar.setBounds(300, 497, 200, 50);
		frame.getContentPane().add(solucionComprobar);
		solucionComprobar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        boolean tieneSolucion = sudokuModelo.resolverSudoku();
		        
		        if (tieneSolucion) {
					JOptionPane.showMessageDialog(frame, 
							"Tiene solucion", 
							"", 
							JOptionPane.INFORMATION_MESSAGE);
		        } else {
					JOptionPane.showMessageDialog(frame, 
							"No tiene solucion", 
							"", 
							JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		
		JButton solucionMostrar = new JButton("Mostrar solución");
		solucionMostrar.addActionListener(e -> {
			controlador.mostrarSolucionIndividualEnElTablero();
		});
		solucionMostrar.setFocusable(false);
		solucionMostrar.setFont(new Font("Arial", Font.BOLD, 14));
		solucionMostrar.setBounds(20, 497, 200, 50);
		frame.getContentPane().add(solucionMostrar);
		
		JButton generaSudoku = new JButton("Generar Sudoku");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 14));
		generaSudoku.setBounds(600, 497, 150, 50);
		frame.getContentPane().add(generaSudoku);
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
					if (cantidad >= 15 && cantidad <= 40) {
						Sudoku sudoku = new Sudoku();
						Controlador controlador = new Controlador();
						new InterfazFrame(controlador, sudoku, cantidad);
						frame.setVisible(false);
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
