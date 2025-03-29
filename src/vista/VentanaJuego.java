package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorJuego;
import modelo.Casilla;

public class VentanaJuego {

    private JFrame frame;
    private JPanel panelTablero;
    private JButton[][] botonesCasillas;
    private final int TAMAÑO = 5;
    private ControladorJuego controlador;

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
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTablero = new JPanel(new GridLayout(TAMAÑO, TAMAÑO));
        frame.add(panelTablero);

        botonesCasillas = new JButton[TAMAÑO][TAMAÑO];

        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                botonesCasillas[i][j] = new JButton();
                final int fila = i, columna = j; // Variables finales para el listener
                botonesCasillas[i][j].addActionListener(e -> {
                    if (controlador != null) {  // Verificamos que el controlador no sea null
                        controlador.manejarClick(fila, columna);
                    }
                });
                panelTablero.add(botonesCasillas[i][j]);
            }
        }
    }
    
    public void setControlador(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    public void actualizarVista(Casilla[][] casillas) {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                botonesCasillas[i][j].setBackground(casillas[i][j].obtenerColor());
            }
        }
    }

    public void mostrar() {
        frame.setVisible(true);
    }
    
}
