package Controlador;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import Modelo.Sudoku;
import Vista.InterfazFrame;
import Vista.SimulacionBusqueda;
import Vista.SimulacionBusquedaDeUnaSolucion;
import Vista.SudokuVisual;
import Vista.Tablero;
import Vista.VisorDeSoluciones;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

public class Controlador {
	
	private Sudoku sudokuModelo;
	private InterfazFrame interfazFrame;
	private SudokuVisual sudokuVisual;
	private Tablero tableroDeSudoku;
	private SimulacionBusquedaDeUnaSolucion simulacionBusquedaUnica;
	private SimulacionBusqueda simulacionBusquedaMultiple;
	
	
	public Controlador() {
	}
	
	public void setSudoku(Sudoku sudoku) {
        this.sudokuModelo = sudoku;
    }

	public void setInterfazFrame(InterfazFrame interfazFrame) {
		this.interfazFrame = interfazFrame;
	}
	
	public void setTablero(Tablero tablero) {
		this.tableroDeSudoku = tablero;
	}
	
	
	public void mostrarSudoku() {
		sudokuModelo.reiniciarSudoku();
		sudokuVisual =  new SudokuVisual(this, sudokuModelo, 0);
		interfazFrame.mostrarPanelSudoku(sudokuVisual);
	}
	
	public void mostrarSudokuAleatorio(int cantidadValoresPrefijados) {
		sudokuVisual =  new SudokuVisual(this, sudokuModelo, cantidadValoresPrefijados);
		if(cantidadValoresPrefijados > 0) {
			sudokuVisual.bloquearEdicionDeCasillas();
		}
		interfazFrame.mostrarPanelSudoku(sudokuVisual);
	}
	
	public void solicitudMostrarMenu() {
		cancelarTodasLasBusquedas();
		mostrarMenu();
	}

	private void cancelarTodasLasBusquedas() {
		if (simulacionBusquedaUnica != null && !simulacionBusquedaUnica.isDone()) {
			simulacionBusquedaUnica.cancel(true);
		}
		
		if (simulacionBusquedaMultiple != null && !simulacionBusquedaMultiple.isDone()) {
			simulacionBusquedaMultiple.cancel(true);
		}
	}
	
	
	public void mostrarMenu() {
		sudokuModelo.reiniciarSudoku();
		interfazFrame.mostrarPanelMenu();
	}

	
	
	public void actualizarValorDeCeldaEnModelo(int fila, int columna, int valor) {
		sudokuModelo.setValorDeLaCelda(fila, columna, valor);
		if(interfazFrame != null) 
			sudokuVisual.actualizarCasillasMarcadas(valor);
			sudokuModelo.aumetarCantidadDeNumerosIngresadosEnElTablero(valor);
		
	}
	
	public void celdaActualizada(JTextField jText, int fila, int col, KeyEvent e) {
		colorearCeldasAfectadas(fila, col);
		int key = e.getKeyChar();
		boolean numeros = key >= 48 && key <= 57;
		if (!seTocoLaTeclaBorrar(key) && (esUnNumeroInvalido(jText, key, numeros) || !this.sudokuModelo.esSeguro(fila,col,key-48))) {
			e.consume();
		} else {
			actualizarValorDeCeldaEnModelo(fila, col, seTocoLaTeclaBorrar(key) ? 0 : (key - 48));
		}
	}
	
	public void celdaSeleccionada(int fila, int col) {
		colorearCeldasAfectadas(fila, col);
	}
	
	public void celdaDeseleccionada() {
		tableroDeSudoku.restablecerColorDeFondo();
	}

	private void colorearCeldasAfectadas(int fila, int col) {
		tableroDeSudoku.colorearCuadrante(fila, col);
		tableroDeSudoku.colorearFila(fila);
		tableroDeSudoku.colorearColumna(col);
	}

	private boolean esUnNumeroInvalido(JTextField jText, int key, boolean numeros) {
		return (!numeros || jText.getText().trim().length() == 1)  || (elNumeroEsCero(key) && jText.getText().trim().length() == 0);
	}

	private boolean seTocoLaTeclaBorrar(int key) {
		return key == 8;
	}

	private boolean elNumeroEsCero(int key) {
		return key == 48;
	}
	


	public void solicitarBuscarSoluciones(int cantidadValoresPrefijados, Tablero tablero, JProgressBar barraDeProceso) {
		this.tableroDeSudoku = tablero;
		tablero.marcarTablero();
		if (cantidadValoresPrefijados >= 17) {
			buscarSolucionUnicaEnElTablero(sudokuModelo, barraDeProceso);
		} else {
			int cantidadDeSoluciones = sudokuVisual.preguntarCantidadDeSoluciones();
			while (cantidadDeSoluciones <= 0) {
				cantidadDeSoluciones = sudokuVisual.preguntarCantidadDeSoluciones();
			}
			mostrarVisorDeSoluciones(sudokuModelo, cantidadValoresPrefijados, tablero, cantidadDeSoluciones);
		}
		
	}

	private void buscarSolucionUnicaEnElTablero(Sudoku sudokuModeloActual,JProgressBar barraDeProceso) {
		this.simulacionBusquedaUnica = new SimulacionBusquedaDeUnaSolucion(barraDeProceso,sudokuModeloActual,this);
		simulacionBusquedaUnica.execute();
	}
	
	public void solicitarBuscarSolucionesMultiples(JProgressBar barraDeProceso, JLabel textBuscado, VisorDeSoluciones visor) throws Exception {
		buscarSolucionesMultiples(barraDeProceso, textBuscado, visor);
	}
	
	private void buscarSolucionesMultiples(JProgressBar barraDeProceso, JLabel textBuscado, VisorDeSoluciones visor) throws Exception {
		this.simulacionBusquedaMultiple = new SimulacionBusqueda(barraDeProceso, sudokuModelo, textBuscado,visor);
		simulacionBusquedaMultiple.execute();
	}
	
	
	public void mostrarMensajeDeSudokuSinSoluciones() {
		sudokuVisual.mostrarMensajeDeSudokuSinSoluciones();
	}
	
	public void mostrarSolucionEnElTablero(int[][] solucion) {
		tableroDeSudoku.actualizarTableroConLaSolucion(solucion);
		tableroDeSudoku.bloquearEdicionDeCasillas();
	}
	
	
	public void mostrarVisorDeSoluciones(Sudoku sudokuModeloActual, int cantidadValoresPrefijados, Tablero tablero, int MAX_SOLUCIONES) {
		sudokuModeloActual.setMaxSolucionesAEncontrar(MAX_SOLUCIONES);
		VisorDeSoluciones visor = new VisorDeSoluciones(this, sudokuModeloActual,tablero);
		interfazFrame.mostrarPanelSoluciones(visor);
	}
	
	public void generarEstadisticas(int val1, int val2, int val3) {   
	    int[] valores = {val1, val2, val3};
	    JFreeChart grafico = crearGrafico(valores);
	    mostrarGrafico(grafico);

	}
	private JFreeChart crearGrafico(int[] valores) {
	    DefaultCategoryDataset datos = new DefaultCategoryDataset();
	    
	    
	    String tiempo = "Tiempo (ms)";

	    for (int i = 0; i < valores.length; i++) {
	    int casillas = valores[i];
	    String casilla = casillas + "";
		long milisegundos = System.currentTimeMillis();
		int segundos = (int) (milisegundos / 1000);
		int tiempoSegunCasillas = segundos / casillas;
	    datos.addValue(tiempoSegunCasillas, tiempo, casilla);
	    }

	    JFreeChart graficoDeBarras = ChartFactory.createBarChart3D(
	        "PRUEBA",
	        "Dificultad por Cantidad de Casillas Prefijadas",
	        "Tiempo promedio de resolución (milisegundos)",
	        datos,
	        PlotOrientation.VERTICAL,
	        true, true, false
	    );

	    return graficoDeBarras;
	}

	public void mostrarGrafico(JFreeChart chart) {
	    ChartPanel panel = new ChartPanel(chart);
	    panel.setPreferredSize(new Dimension(700, 500));
	    panel.setMouseWheelEnabled(true);
	    JFrame frameDelGrafico = new JFrame("Resultados de Estadísticas");
	    frameDelGrafico.setContentPane(panel);
	    frameDelGrafico.pack();
	    frameDelGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frameDelGrafico.setVisible(true);
	    frameDelGrafico.repaint();
	}

	public void buscarSolucionUnica(JProgressBar barraDeProceso, JLabel textBuscando,
			VisorDeSoluciones visorDeSoluciones) {
		// TODO Auto-generated method stub
		
	}

	
	
}
