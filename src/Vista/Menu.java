package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Sudoku;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected JButton ComoJugarButton;
	private Color color; 
	
	public Menu(Controlador controlador, Sudoku sudoku) {
		setBounds(100, 100, 800, 600);
		setBackground(new Color(206, 175, 174));
		setLayout(null);
		color = new Color(231, 211, 175);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/media/Wombats_Solving_Sudoku_Together-removebg-preview (1).png"));
		lblNewLabel.setBounds(59, 48, 402, 265);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sudoku ");
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 70));
		lblNewLabel_1.setBounds(460, 83, 279, 82);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Resolver");
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 70));
		lblNewLabel_2.setBounds(460, 146, 295, 102);
		add(lblNewLabel_2);
		

		JButton generaSudoku = new JButton("Generar Manualmente");
		generaSudoku.setFocusable(false);
		generaSudoku.setFont(new Font("Arial", Font.BOLD, 18));
		generaSudoku.setBackground(color);
		generaSudoku.setBounds(102, 353, 245, 66);
		add(generaSudoku);
		generaSudoku.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarSudoku();
			}
	});
		JButton generaSudokuAleatorio = new JButton("Generar Aleatoriamente");
		generaSudokuAleatorio.setFocusable(false);
		generaSudokuAleatorio.setFont(new Font("Arial", Font.BOLD, 18));
		generaSudokuAleatorio.setBackground(color);
		generaSudokuAleatorio.setBounds(102, 432, 245, 66);
		add(generaSudokuAleatorio);
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
							controlador.mostrarSudokuAleatorio(cantidad);
						}
						else {
							Component frame = null;
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
		add(manual);
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
		add(estadisticas);
		
	}
	
}
