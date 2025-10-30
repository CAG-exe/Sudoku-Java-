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

import javax.script.ScriptContext;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VisorDeSoluciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar barraDeProceso;
	private Sudoku sudoku;
	private Controlador controlador;
	private Tablero tablero;
	private JLabel TextBuscando;
	private JScrollPane scrollPaneDeBotones;
	private int valoresPrefijados = 16;
	private JPanel PanelInferior;
	private JLabel tiempo;
	private int segundosTranscurridos;
	private boolean tiempoActivo;
	private Timer timer;

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
		setResizable(false);
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
		panelDeTablero.setBounds(375, 75, 579, 508);
		contentPane.add(panelDeTablero);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 934, 77);
		contentPane.add(panel);
		
		barraDeProceso = new JProgressBar();
		
		this.TextBuscando = new JLabel("Buscando soluciones...");
		TextBuscando.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TextBuscando.setHorizontalAlignment(JLabel.CENTER);
		
		tiempo = new JLabel("00:00");
		tiempo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tiempo.setHorizontalAlignment(JLabel.RIGHT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(barraDeProceso, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(tiempo, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
							.addComponent(TextBuscando, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
							.addGap(354))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(TextBuscando, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(tiempo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barraDeProceso, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		panelDeTablero.setLayout(null);

		
		
		this.tablero = new Tablero(controlador, sudokuModelo, valoresPrefijados);
		tablero.bloquearEdicionDeCasillas();
		tablero.setBounds(-111, 0, 724, 450);
		panelDeTablero.add(tablero);
		
		PanelInferior = new JPanel();
		PanelInferior.setBackground(new Color(240, 240, 240));
		PanelInferior.setBounds(0, 449, 579, 59);
		panelDeTablero.add(PanelInferior);
		
		
		this.scrollPaneDeBotones = new JScrollPane();
		scrollPaneDeBotones.setBounds(10, 75, 367, 508);
		scrollPaneDeBotones.setBorder(null);
		contentPane.add(scrollPaneDeBotones);
		scrollPaneDeBotones.getVerticalScrollBar().setUnitIncrement(16);
		buscarSoluciones(barraDeProceso);
		
	}

	private void buscarSoluciones(JProgressBar barraDeProceso) throws Exception {
		SimulacionBusqueda busqueda = new SimulacionBusqueda(barraDeProceso, sudoku, TextBuscando,this);
		busqueda.execute();
	}
	
	
	public void crearBotonesDeSoluciones(List<Matriz> soluciones) {
		PanelDeBotones panelDeBotones = new PanelDeBotones(scrollPaneDeBotones, soluciones,controlador,tablero);
		panelDeBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelDeBotones.crearBotonesDeSoluciones();
		scrollPaneDeBotones.setPreferredSize(panelDeBotones.getSize());
		scrollPaneDeBotones.setViewportView(panelDeBotones);
	}
	
	public void setTiempo(int segundos) {
		int min = segundos / 60;
		int sec = segundos % 60;
		String tiempoFormateado = String.format("%02d:%02d", min, sec);
		this.tiempo.setText(tiempoFormateado);
	}
	
	public void iniciarCronometro() {
		segundosTranscurridos = 0;
		tiempoActivo = true;
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tiempoActivo) {
					segundosTranscurridos++;
					setTiempo(segundosTranscurridos);
				}
			}
			
		});
		
		timer.start();
	}
	
	public void detenerCronometro() {
		tiempoActivo = false;
		if (timer != null) {
			timer.stop();
		}
	}
	
	
}
