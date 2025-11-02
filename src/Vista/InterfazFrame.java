package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controlador.Controlador;
import Modelo.Sudoku;

public class InterfazFrame extends JFrame{

	private Tablero tablero;
	private Controlador controlador;
	private Sudoku sudokuModelo;
	private int cantidadValoresPrefijados;
	private Menu menu;
	

	public InterfazFrame(Controlador controlador, Sudoku sudokuModelo) {
		this.controlador = controlador;
		this.sudokuModelo = sudokuModelo;
		controlador.setSudoku(sudokuModelo);
		controlador.setInterfazFrame(this);
		inicializar();
	}
	
	private void inicializar() {
		setResizable(false);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		menu = new Menu(controlador,sudokuModelo);
		menu.setSize(784, 561);
		
		mostrarPanelMenu();
}


	
	public void mostrarPanelMenu() {
		menu.setLocation(0, 0);
		getContentPane().removeAll();
		setSize(800, 600);
		setTitle("Sudoku-Menu");
		getContentPane().add(menu, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void mostrarPanelSudoku(JPanel juego) {
		juego.setLocation(0, 0);
		getContentPane().removeAll();
		setSize(800, 600);
		getContentPane().add(juego, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void mostrarPanelSoluciones(JPanel soluciones) {
		soluciones.setLocation(0, 0);
		getContentPane().removeAll();
		setSize(970, 622);
		getContentPane().add(soluciones, BorderLayout.CENTER);
		revalidate();
		repaint();
		System.out.println("No FUNCIONA");
	}
}
