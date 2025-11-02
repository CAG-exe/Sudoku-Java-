package Vista;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controlador.Controlador;
import Modelo.Matriz;

public class PanelDeBotones extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneDeBotones;
	private List<Matriz> soluciones;
	private Controlador controlador;
	private Tablero tablero;
	private int altura = 0;

	public PanelDeBotones(JScrollPane scrollPaneDeBotones, List<Matriz> soluciones, Controlador controlador,
			Tablero tablero) {
		this.scrollPaneDeBotones = scrollPaneDeBotones;
		this.soluciones = soluciones;
		this.controlador = controlador;
		this.tablero = tablero;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void crearBotonesDeSoluciones() {
		for (int i = 0; i < soluciones.size(); i++) {
			altura += 70;
			int indice = i;
			JButton botonDeSolucion = new JButton("Solución " + (i + 1));
			botonDeSolucion.setAlignmentX(CENTER_ALIGNMENT);
			botonDeSolucion.setMaximumSize(new Dimension(250, 70));
			botonDeSolucion.setMinimumSize(new Dimension(250, 70));
			botonDeSolucion.addActionListener(e -> {
				controlador.mostrarSolucionIndividualEnElTablero(tablero,soluciones.get(indice).clonar());
			});
			this.add(botonDeSolucion);
		}
		setPreferredSize(new Dimension(250, altura));
		this.revalidate();
		this.repaint();
	}

}
