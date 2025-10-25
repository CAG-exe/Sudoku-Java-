package Main;

import java.awt.EventQueue;

import Controlador.Controlador;
import Modelo.Sudoku;
import Vista.InterfazFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controlador controlador = new Controlador();
					Sudoku sudokuModelo = new Sudoku();
					InterfazFrame window = new InterfazFrame(controlador, sudokuModelo, 20);
					controlador.setSudoku(sudokuModelo);
					controlador.setInterfazFrame(window);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
