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
        manualTexto.setFont(new Font("Monospaced", Font.PLAIN, 15));
        manualTexto.setEditable(false);
        
        String manualContenido = 
        "               MANUAL: SUDOKU             \n" + "\n" +
        "1. MENÚ PRINCIPAL" + "\n" +
        "\n" +
        "Al iniciar, puede elegir cómo comenzar el juego:" + "\n" + "\n" +
        "- Generar: Pide ingresar una cantidad de valores prefijados." + "\n" +
        "El rango válido es entre 15 y 40." + "\n" + "\n" +
        "- Generar Aleatoriamente: Inicia un juego con valores prefijados" + "\n" + 
        "al azar (entre 15 y 40)." + "\n" + "\n" +
        "2. INTERFAZ DEL JUEGO" + "\n" +"\n" +
        "Una vez en el tablero, tiene los siguientes controles:" + "\n" + "\n" +
        "- Comprobar solución: Verifica si su tablero tiene" + "\n" +
        "al menos una solución posible." + "\n" + "\n" +
        "- Mostrar solución: Rellena el tablero con la solución encontrada." + "\n" + "\n" +
        "- Generar Sudoku: Inicia un nuevo juego pidiendo una cantidad" + "\n" + 
        "de valores prefijados." + "\n" + "\n" +
        "3. INTERACCIÓN CON EL TABLERO" + "\n" + "\n" +
        "- Casillas Prefijadas: Son los números iniciales" + "\n" +
        "No pueden ser editados." + "\n" + "\n" +
        "- Si un número se repite en fila,columna o cuadrante, no se lo tomará." + 
        "\n" + "\n" +
        "- Al seleccionar una celda, su fila, columna y cuadrante" + "\n" + 
        "son resaltadas para facilitar la visualización de restricciones." + "\n" +
        "\n";
        
        manualTexto.setText(manualContenido);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(manualTexto);
        add(scrollPane);

        setVisible(true);
    }
}