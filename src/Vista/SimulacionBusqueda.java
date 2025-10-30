package Vista;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import Modelo.Matriz;
import Modelo.Sudoku;

public class SimulacionBusqueda extends SwingWorker<List<Matriz>, List<Matriz>> {
	
	
	private JProgressBar barra;
	private Sudoku sudoku;
	private JLabel texto;
	private List<Matriz> soluciones;
	private VisorDeSoluciones visorDeSoluciones;

	public SimulacionBusqueda(JProgressBar barra, Sudoku sudoku, JLabel texto, VisorDeSoluciones visorDeSoluciones) {
		this.visorDeSoluciones = visorDeSoluciones;
		this.barra = barra;
		this.sudoku = sudoku;
		this.texto = texto;
	}
	
	@Override
	protected List<Matriz> doInBackground() throws Exception {
		visorDeSoluciones.iniciarCronometro();
		barra.setIndeterminate(true);
		sudoku.resolverSudoku();
		this.soluciones = sudoku.getSoluciones();
		return soluciones;
	}
	@Override
	public void done() {
		if(soluciones.size()>0) {
			this.texto.setText("    ¡ENCONTRADOS!");
			visorDeSoluciones.crearBotonesDeSoluciones(soluciones);
		} else {
			this.texto.setText("¡NO TIENE SOLUCION!");
		}
		barra.setIndeterminate(false);
		visorDeSoluciones.detenerCronometro();
	}

}
