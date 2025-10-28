package Vista;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import Controlador.Controlador;
import Modelo.Matriz;

public class JPanelDeBotones extends JPanel implements Scrollable {

	public JPanelDeBotones(List<Matriz> soluciones, Tablero tablero, Controlador controlador) {
		setBounds(10, 75, 367, 508);
        soluciones.add(soluciones.get(0));
        
        for(int i=0; i<soluciones.size(); i++) {
            JButton botonDeSolucion = new JButton("Solucion " + (i+1));
            final int indice = i;
            botonDeSolucion.addActionListener(e -> {
                Matriz solucionSeleccionada = soluciones.get(indice);
                tablero.actualizarTableroConLaSolucion(solucionSeleccionada.clonar());
            });
            add(botonDeSolucion);
        }
        
        revalidate();
        repaint();
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

}
