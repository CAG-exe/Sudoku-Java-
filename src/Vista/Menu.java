package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import Controlador.Controlador;
import Modelo.Sudoku;

public class Menu{
	
	protected JButton ComoJugarButton;
	private JFrame frame;
	private Color color; 
	
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
		color = new Color(231, 211, 175);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/media/Wombats_Solving_Sudoku_Together-removebg-preview (1).png"));
		lblNewLabel.setBounds(59, 48, 402, 265);
		frame.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sudoku ");
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 70));
		lblNewLabel_1.setBounds(460, 83, 235, 82);
		frame.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Resolver");
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 70));
		lblNewLabel_2.setBounds(460, 146, 235, 102);
		frame.add(lblNewLabel_2);
		

		JButton generaSudoku = new JButton("Generar Manualmente");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 18));
		generaSudoku.setBackground(color);
		generaSudoku.setBounds(102, 353, 245, 66);
		frame.add(generaSudoku);
		generaSudoku.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sudoku sudoku = new Sudoku();
				Controlador controlador = new Controlador();
				new InterfazFrame(controlador, sudoku, 0);
			}
	});
		JButton generaSudokuAleatorio = new JButton("Generar Aleatoriamente");
		generaSudokuAleatorio.setFocusable(false);
		generaSudokuAleatorio.setFont(new Font("Arial", Font.BOLD, 18));
		generaSudokuAleatorio.setBackground(color);
		generaSudokuAleatorio.setBounds(102, 432, 245, 66);
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
		manual.setFont(new Font("Arial", Font.BOLD, 20));
		manual.setBackground(color);
		manual.setBounds(425, 430, 235, 66);
		frame.add(manual);
		manual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManualVentana();
				
			}
	});
		
		JButton estadisticas = new JButton("Estadisticas");
		estadisticas.setFocusable(false);
		estadisticas.setFont(new Font("Arial", Font.BOLD, 18));
		estadisticas.setBackground(color);
		estadisticas.setBounds(425, 353, 235, 66);
		frame.add(estadisticas);
		
	}
	private int generarCantidadPrefijadaAleatoria() {
		Random random = new Random();
		int numero = (random.nextInt(26)) + 15;
		return numero;
	}
	
}
