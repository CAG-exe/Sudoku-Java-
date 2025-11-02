package Vista;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ManualVentana extends JFrame {

    private static final long serialVersionUID = 1L;

	public ManualVentana() {
        setTitle("Manual del Juego de Sudoku");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea manualTexto = new JTextArea();
        manualTexto.setFont(new Font("Arial", Font.PLAIN, 24));
        manualTexto.setEditable(false);
        
        String manualContenido = 
        "               MANUAL: SUDOKU             \n" + "\n" +
        "1. MENÚ PRINCIPAL" + "\n" +
        "\n" +
        "Al iniciar, puede elegir cómo comenzar el juego:" + "\n" + "\n" +
        "- Generar Manualmente: Puedes ingresar valores prefijados" + "\n" +
        "de forma manual" + "\n" + "\n" +
        "- Generar Aleatoriamente: Pide ingresar una cantidad de valores prefijados." + "\n" + 
        "El rango válido es entre 15 y 40." + "\n" + "\n" +
        "2. INTERFAZ DEL JUEGO" + "\n" +"\n" +
        "Una vez en el tablero, tienes el siguiente botón:" + "\n" + "\n" +
        "- Buscar soluciones: Busca todas las soluciones posibles del sudoku y"
        + "\n" + "lo mostrará; caso contrario, no se observará ninguna solucion." +
        "\n" + "\n" +
        "3. INTERACCIÓN CON EL TABLERO" + "\n" + "\n" +
        "- Casillas Prefijadas: Son los números iniciales." + "\n" +
        "No pueden ser editados." + "\n" + "\n" +
        "- Si un número se repite en fila,columna o cuadrante, no se lo tomará." + 
        "\n" + "\n" +
        "- Al seleccionar una celda su fila, columna y cuadrante" + "\n" + 
        "son resaltadas para facilitar la visualización de restricciones." + "\n" +
        "\n";
        
        manualTexto.setText(manualContenido);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(manualTexto);
        add(scrollPane);

        setVisible(true);
    }
}