package Vista;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import Controlador.Controlador;
import Modelo.Matriz;
import Modelo.Sudoku;

public class SimulacionBusquedaDeUnaSolucion  extends SwingWorker<List<Matriz>, List<Matriz>>{

	private JProgressBar barra;
	private Sudoku sudoku;
	private List<Matriz> soluciones;
	private Controlador controlador;

	public SimulacionBusquedaDeUnaSolucion(JProgressBar barra, Sudoku sudoku, Controlador controlador) {
		this.barra = barra;
		this.sudoku = sudoku;
		this.controlador = controlador;
	}
	
	@Override
	protected List<Matriz> doInBackground() throws Exception {
		barra.setIndeterminate(true);
		sudoku.resolverSudoku();
		this.soluciones = sudoku.getSoluciones();
		return soluciones;
	}
	
	@Override
	public void done() {
		barra.setIndeterminate(false);
		if(this.soluciones.isEmpty()) {
			controlador.mostrarMensajeDeSudokuSinSoluciones();
		} else {
			controlador.mostrarSolucionEnElTablero(sudoku.getUnicaSolucion());
		}
	}
}
