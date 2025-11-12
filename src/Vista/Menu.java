package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controlador.Controlador;
import Modelo.Sudoku;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected JButton ComoJugarButton;
	private Color color; 
	private int minimoPrefijados = 0;
	private int maximoPrefijados = 40;
	
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
						if (cantidad >= minimoPrefijados && cantidad <= maximoPrefijados) {
							controlador.mostrarSudokuAleatorio(cantidad);
						}
						else {
							Component frame = null;
							JOptionPane.showMessageDialog(frame, 
									"Entre 0 y 40.", 
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
		estadisticas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = new Font("Arial", Font.BOLD, 15);
				
				JDialog opcionesEstadisticas = new JDialog();
				opcionesEstadisticas.setTitle("Configuracion de Estadisticas");
				opcionesEstadisticas.setSize(400, 600);
				opcionesEstadisticas.getContentPane().setBackground(new Color(206, 175, 174));
				opcionesEstadisticas.setLayout(null);
				opcionesEstadisticas.setResizable(false);
				
				JLabel matriz1Label = new JLabel("Increse casillas de la primera matriz:");
				configLabelJDialog(font, matriz1Label,0,false);
				opcionesEstadisticas.add(matriz1Label);
				
				JLabel matriz2Label = new JLabel("Increse casillas de la segunda matriz:");
				configLabelJDialog(font, matriz2Label,100,false);
				opcionesEstadisticas.add(matriz2Label);
				
				JLabel matriz3Label = new JLabel("Increse casillas de la tercera matriz:");
				configLabelJDialog(font, matriz3Label,200,false);
				opcionesEstadisticas.add(matriz3Label);
				
				JTextField matriz1Field = new JTextField();
				matriz1Field.setBounds(50, 90, 300, 30);
				matriz1Field.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						ingresoOSoloNumeros(e);
					}
				});
				opcionesEstadisticas.add(matriz1Field);
				JTextField matriz2Field = new JTextField();
				matriz2Field.setBounds(50, 190, 300, 30);
				matriz2Field.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						ingresoOSoloNumeros(e);
					}
				});
				opcionesEstadisticas.add(matriz2Field);
				JTextField matriz3Field = new JTextField();
				matriz3Field.setBounds(50, 290, 300, 30);
				matriz3Field.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						ingresoOSoloNumeros(e);
					}
				});
				JTextArea info = new JTextArea(
			            " Si la cantidad de valores prefijados\n es mayor o igual a 17, buscará una\n única solución."
			        );
				info.setEditable(false);
				info.setOpaque(false);
				info.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
				info.setBounds(20, 350, 330, 80); 
				info.setForeground(new Color(64,60,60));
		        opcionesEstadisticas.add(info);	
		        
				opcionesEstadisticas.add(matriz3Field);
				
				String errorText = "las casillas deben ser entre 0 y 81";
				
				JLabel error1Label = new JLabel(errorText);
				configLabelJDialog(font, error1Label,0, true);
				error1Label.setVisible(false);
				opcionesEstadisticas.add(error1Label);
				
				JLabel error2Label = new JLabel(errorText);
				configLabelJDialog(font, error2Label,100, true);
				error2Label.setVisible(false);
				opcionesEstadisticas.add(error2Label);
				
				JLabel error3Label = new JLabel(errorText);
				configLabelJDialog(font, error3Label,200, true);
				error3Label.setVisible(false);
				opcionesEstadisticas.add(error3Label);
				
				
				JButton estadisticasButton = new JButton("Generar Estadisticas");
				estadisticasButton.setBounds(43, 450, 300, 50);
				estadisticasButton.setFont(new Font("Arial", Font.BOLD, 20));
				estadisticasButton.setBackground(color);
				estadisticasButton.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						boolean error1 = esValorvalido(matriz1Field.getText());
					    boolean error2 = esValorvalido(matriz2Field.getText());
					    boolean error3 = esValorvalido(matriz3Field.getText());

					    error1Label.setVisible(error1);
					    error2Label.setVisible(error2);
					    error3Label.setVisible(error3);
					    
					    if(!error1 && !error2 && !error3) {
						int valor1 = Integer.parseInt(matriz1Field.getText());
				        int valor2 = Integer.parseInt(matriz2Field.getText());
				        int valor3 = Integer.parseInt(matriz3Field.getText());
				        String cantSoluciones = JOptionPane.showInputDialog(
					            opcionesEstadisticas,
					            "¿Cuántas soluciones quieres encontrar?",
					            "",
					            JOptionPane.QUESTION_MESSAGE
					        );
				        if (cantSoluciones != null) {
				        	int cantidadSoluciones = Integer.parseInt(cantSoluciones.trim());
				        	opcionesEstadisticas.dispose();
				        	controlador.generarEstadisticas(valor1, valor2, valor3,cantidadSoluciones);
					    }
					}
				}
				});
				opcionesEstadisticas.add(estadisticasButton);
				opcionesEstadisticas.setModal(true);
				opcionesEstadisticas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				opcionesEstadisticas.setLocationRelativeTo(null);
				opcionesEstadisticas.setVisible(true);
			}

		});
		add(estadisticas);
		

	}
	private void ingresoOSoloNumeros(KeyEvent e) {
		int key = e.getKeyChar();
		boolean numeros = key >= 48 && key <= 57;
		if (!seTocoLaTeclaBorrar(key) && numeros == false) {
			e.consume();
		}
	}
	
	private boolean seTocoLaTeclaBorrar(int key) {
		return key == 8;
	}
	
	private void configLabelJDialog(Font font, JLabel label, int pos, boolean error) {
	    if (error) {
	        label.setBounds(50, 120 + pos, 300, 30);
	        label.setForeground(Color.RED);
	    } else {
	        label.setBounds(50, 50 + pos, 300, 30);
	        label.setForeground(Color.BLACK);
	    }
	    label.setFont(font);
	}
	
	private boolean esValorvalido(String text) {
		try {
			int valor = Integer.parseInt(text.trim());
			if (valor >= 0 && valor <= 81) {
				return false;
			} 
			else {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
	}

}
