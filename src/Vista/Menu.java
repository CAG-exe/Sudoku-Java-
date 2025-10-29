package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import Controlador.Controlador;
import Modelo.Sudoku;

public class Menu{
	
	protected JButton ComoJugarButton;
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    UIManager.setLookAndFeel( new FlatMaterialLighterIJTheme() );
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize LaF" );
				}
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
		

		JButton generaSudoku = new JButton("Generar Manualmente");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 14));
		generaSudoku.setBounds(300, 40, 200, 100);
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
						if (cantidad >= 15 && cantidad <= 40) {
							Sudoku sudoku = new Sudoku();
							Controlador controlador = new Controlador();
							new InterfazFrame(controlador, sudoku, cantidad);
						}
						else {
							JOptionPane.showMessageDialog(frame, 
								"Entre 15 y 40.", 
								"", 
								JOptionPane.ERROR_MESSAGE);
						}
					}
			}
	});
		JButton generaSudokuAleatorio = new JButton("Generar Aleatoriamente");
		generaSudokuAleatorio.setFocusable(false);
		generaSudokuAleatorio.setFont(new Font("Arial", Font.BOLD, 14));
		generaSudokuAleatorio.setBounds(297, 230, 203, 100);
		frame.add(generaSudokuAleatorio);
		generaSudokuAleatorio.addActionListener(new ActionListener() {
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
						}
						else {
							JOptionPane.showMessageDialog(frame, 
								"Entre 15 y 40.", 
								"", 
								JOptionPane.ERROR_MESSAGE);
						}
					}
			}
	});
		
		JButton manual = new JButton("Manual");
		manual.setFocusable(false);
		manual.setFont(new Font("Arial", Font.BOLD, 14));
		manual.setBounds(300, 420, 200, 100);
		frame.add(manual);
		manual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManualVentana();
				
			}
	});
		
		//JLabel logoSudoku = new JLabel("");
		//ImageIcon Logo = new ImageIcon(getClass().getResource("/media/sudoku.png"));
		//logoSudoku.setIcon(Logo);
		//logoSudoku.setBounds(150, 41, 500, 400);
		//frame.add(logoSudoku);
        
	}
	private int generarCantidadPrefijadaAleatoria() {
		Random random = new Random();
		int numero = (random.nextInt(26)) + 15;
		return numero;
	}
	
}
