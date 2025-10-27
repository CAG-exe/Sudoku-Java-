package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import Controlador.Controlador;
import Modelo.Matriz;
import Modelo.Sudoku;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VisorDeSoluciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar barraDeProceso;
	private Sudoku sudoku;
	private Controlador controlador;
	private Tablero tablero;
	private JLabel TextBuscando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatMaterialLighterIJTheme() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Controlador controlador = new Controlador();
					Sudoku sudokuModelo = new Sudoku();
					VisorDeSoluciones frame = new VisorDeSoluciones(controlador, sudokuModelo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public VisorDeSoluciones(Controlador controlador, Sudoku sudokuModelo) throws Exception {
		this.sudoku = sudokuModelo;
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 622);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelDeTablero = new JPanel();
		contentPane.add(panelDeTablero);
		panelDeTablero.setBounds(375, 75, 579, 508);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 934, 77);
		contentPane.add(panel);
		
		barraDeProceso = new JProgressBar();
		
		this.TextBuscando = new JLabel("Buscando soluciones...");
		TextBuscando.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(barraDeProceso, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(TextBuscando, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
							.addGap(354))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addComponent(TextBuscando, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barraDeProceso, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		panelDeTablero.setLayout(null);

		
		
		this.tablero = new Tablero(controlador, sudokuModelo, 15);
		tablero.bloquearEdicionDeCasillas();
		tablero.setBounds(-111, 0, 724, 583);
		panelDeTablero.add(tablero);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 367, 508);
		contentPane.add(scrollPane);
		
		buscarSoluciones(barraDeProceso);
		
	}

	private void buscarSoluciones(JProgressBar barraDeProceso) throws Exception {
		SimulacionBusqueda busqueda = new SimulacionBusqueda(barraDeProceso, sudoku, TextBuscando);
		busqueda.execute();
	}
}
