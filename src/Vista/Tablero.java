package Vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
	//private Sudoku sudokuModelo;
	private Color colorFondoCasillasPrefijadas = new Color(195, 215, 234);
	private Color colorFondoCasillas = Color.WHITE;
	private Color colorFondoResaltado = new Color(225, 245, 255);

	public Tablero(Controlador controlador, Sudoku sudokuModelo, int cantidadValoresPrefijados) {
		this.controlador = controlador;
		//this.sudokuModelo = sudokuModelo;
		controlador.setTablero(this);
		setLayout(null);
		Sudoku su = sudokuModelo;
		sudoku = su.getMatrizJuego();
		matrizGUI = new JTextField[9][9]; 
		constructorDeMatriz();
		if(cantidadValoresPrefijados>=0) {
			sudoku.marcarCasillasConNumerosValidos(cantidadValoresPrefijados);
		}
		marcarTablero();
		setBackground(new Color(240, 240, 240));
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
				casilla.setBackground(colorFondoCasillas);
				valorValido(casilla,fila,col);
				celdaEsSelecionada(fila, col, casilla);
				
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
	
	private void celdaEsSelecionada(int fila, int col, JTextField casilla) {
		casilla.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		    	controlador.celdaSeleccionada(fila, col);
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		    	controlador.celdaDeseleccionada();
		    }
		});
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
	
	FocusListener miListenerDeFoco = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            System.out.println("El foco se ganó");
        }

        @Override
        public void focusLost(FocusEvent e) {
            System.out.println("El foco se perdió");
        }
    };
    
    
	
	public void marcarTablero() {
		int[][] copia = sudoku.clonar();
		for(int fila=0 ; fila<9 ; fila++) {
			for(int col=0 ; col<9 ; col++) {
				if(copia[fila][col]!=0) {
					matrizGUI[fila][col].setText(copia[fila][col]+"");
					matrizGUI[fila][col].setEditable(false);
					matrizGUI[fila][col].setEnabled(false);
					matrizGUI[fila][col].setForeground(Color.BLACK); 
					matrizGUI[fila][col].setDisabledTextColor(Color.BLACK);
					matrizGUI[fila][col].setBackground(colorFondoCasillasPrefijadas);
			}
		}
	}
	}
	
	public void restablecerColorDeFondo() {
		for (int fila = 0; fila < 9; fila++) {
			for (int col = 0; col < 9; col++) {
				if (!esUnaCasillaPrefijada(fila, col)) {
					matrizGUI[fila][col].setBackground(colorFondoCasillas);
				}
			}
		}
	}
	
	
	public void colorearFila(int fila) {
		for (int col = 0; col < 9; col++) {
			if (!esUnaCasillaPrefijada(fila, col))
				matrizGUI[fila][col].setBackground(colorFondoResaltado);
		}
	}
	
	public void colorearColumna(int col) {
        for (int fila = 0; fila < 9; fila++) {
        	if (!esUnaCasillaPrefijada(fila, col))
        		matrizGUI[fila][col].setBackground(colorFondoResaltado);
        }
	}
	
	public void colorearCuadrante(int fila, int col) {
		int filaInicio = (fila / 3) * 3;
		int colInicio = (col / 3) * 3;

		for (int i = filaInicio; i < filaInicio + 3; i++) {
			for (int j = colInicio; j < colInicio + 3; j++) {
				if (!esUnaCasillaPrefijada(i, j))
					matrizGUI[i][j].setBackground(colorFondoResaltado);
			}
		}
	}
	
	private boolean esUnaCasillaPrefijada(int fila, int col) {
		return matrizGUI[fila][col].getBackground().equals(colorFondoCasillasPrefijadas);
	}
	
	public void actualizarTableroConLaSolucion(int[][] matrizSolucion) {
		for (int fila = 0; fila < 9; fila++) {
			for (int col = 0; col < 9; col++) {
				matrizGUI[fila][col].setText(matrizSolucion[fila][col] + "");
			}
		}
	}
	
	public void bloquearEdicionDeCasillas() {
		for (int fila = 0; fila < 9; fila++) {
			for (int col = 0; col < 9; col++) {
				matrizGUI[fila][col].setEditable(false);
				matrizGUI[fila][col].setEnabled(false);
			}
		}
	}
}

