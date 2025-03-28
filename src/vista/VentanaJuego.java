package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego {

    private JFrame frame;
    private JPanel panelTablero;
    private JButton[][] botonesCasillas;
    private final int TAMAÑO = 5;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaJuego window = new VentanaJuego();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaJuego() {
        inicializar();
    }

    private void inicializar() {
        frame = new JFrame("Locura Cromática");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        // Create and configure the board panel
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(TAMAÑO, TAMAÑO, 2, 2)); // Added gaps between buttons
        
        // Initialize and add buttons to the panel
        botonesCasillas = new JButton[TAMAÑO][TAMAÑO];
        for (int fila = 0; fila < TAMAÑO; fila++) {
            for (int columna = 0; columna < TAMAÑO; columna++) {
                botonesCasillas[fila][columna] = new JButton();
                panelTablero.add(botonesCasillas[fila][columna]);
            }
        }
        
        // Add the panel to the frame
        frame.getContentPane().add(panelTablero);
        
        // Center the window
        frame.setLocationRelativeTo(null);
    }
}
