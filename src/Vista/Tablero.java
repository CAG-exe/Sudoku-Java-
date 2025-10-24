package Vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

import Controlador.Controlador;
import Modelo.Matriz;
import Modelo.Sudoku;

public class Tablero extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField[][]  matrizGUI;
	private Matriz sudoku;;
	private Controlador controlador;
	private Sudoku sudokuModelo;

	public Tablero(Controlador controlador, Sudoku sudokuModelo, int cantidadValoresPrefijados) {
		this.controlador = controlador;
		this.sudokuModelo = sudokuModelo;

		setLayout(null);
		Sudoku su = sudokuModelo;
		sudoku = su.getMatrizJuego();
		sudoku.marcarCasillasConNumerosValidos(cantidadValoresPrefijados);
		matrizGUI = new JTextField[9][9]; 
		su.resolverSudoku();
		constructorDeMatriz();
		marcarTablero();
		setBackground(new Color(206, 175, 174));
		
		
	}
	
	private void constructorDeMatriz() {
		int x=220;
		int y=80;
		for(int fila=0 ; fila<9 ; fila++) {
			for(int col=0 ; col<9 ; col++) {	
				JTextField casilla = new JTextField();
				casilla.setBounds(x,y,40,40);
				casilla.setHorizontalAlignment(SwingConstants.CENTER);
				casilla.setFont(new Font("Tahoma", Font.PLAIN, 20));
				valorValido(casilla,fila,col);
				x+=40;
				
		        int arriba = (fila % 3 == 0) ? 3 : 1;
		        int izq = (col % 3 == 0) ? 3 : 1;
		        int abajo = (fila == 8) ? 3 : 1;
		        int der = (col == 8) ? 3 : 1;

		        casilla.setBorder(BorderFactory.createMatteBorder(arriba, izq, abajo, der, Color.BLACK));
				add(casilla);
				matrizGUI[fila][col] = casilla;
				}
			x=220;
			y+=40;
		
		}
	}
	private void valorValido(JTextField jText,int fila,int col) {
		jText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				controlador.celdaActualizada(jText, fila, col, e);
				
			}
			
		}
		);
	}
	
	private void marcarTablero() {
		int[][] copia = sudoku.clonar();
		for(int fila=0 ; fila<9 ; fila++) {
			for(int col=0 ; col<9 ; col++) {
				if(copia[fila][col]!=0) {
					matrizGUI[fila][col].setText(copia[fila][col]+"");
					matrizGUI[fila][col].setEditable(false);
					matrizGUI[fila][col].setEnabled(false);
					matrizGUI[fila][col].setForeground(Color.BLACK); 
					matrizGUI[fila][col].setDisabledTextColor(Color.BLACK);
					matrizGUI[fila][col].setBackground(new Color(195, 215, 234));;
			}
		}
	}
	}
}

