package Main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import Controlador.Controlador;
import Modelo.GeneradorAleatorio;
import Modelo.Sudoku;
import Vista.InterfazFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    UIManager.setLookAndFeel( new FlatMaterialLighterIJTheme() );
				} catch( Exception ex ) {
				    System.err.println( "Failed to initialize LaF" );
				}
				try {
					Controlador controlador = new Controlador();
					Sudoku sudokuModelo = new Sudoku(new GeneradorAleatorio());
					InterfazFrame window = new InterfazFrame(controlador, sudokuModelo);
					controlador.setSudoku(sudokuModelo);
					controlador.setInterfazFrame(window);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
