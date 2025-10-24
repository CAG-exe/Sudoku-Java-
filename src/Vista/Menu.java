package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controlador.Controlador;
import Modelo.Sudoku;

public class Menu{
	
	protected JButton ComoJugarButton;
	private JFrame frame;
	private Tablero tablero;
	private Controlador controlador;
	private Sudoku sudoku;
	

	/**
	 * Create the panel.
	 * @param controladorPrincipal 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Menu() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.getContentPane().setBackground(new Color(206, 175, 174));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Sudoku-Menu");

		JButton generaSudoku = new JButton("Generar");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 14));
		generaSudoku.setBounds(300, 450, 200, 100);
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
						Sudoku sudoku = new Sudoku();
						Controlador controlador = new Controlador();
						new InterfazFrame(controlador, sudoku, cantidad);
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
		//JLabel logoSudoku = new JLabel("");
		//ImageIcon Logo = new ImageIcon(getClass().getResource("/media/sudoku.png"));
		//logoSudoku.setIcon(Logo);
		//logoSudoku.setBounds(150, 41, 500, 400);
		//frame.add(logoSudoku);
        
	}
	
}
