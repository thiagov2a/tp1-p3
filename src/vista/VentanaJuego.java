package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorJuego;
import modelo.Casilla;
import modelo.ColorCasilla;
import modelo.Tablero;

public class VentanaJuego {

    private JFrame frame;
    private JPanel panelTablero;
	private JLabel contadorIntentos;
    private JButton[][] botonesCasillas;
    private final int TAMAÑO = 5;
    private ControladorJuego controlador;
    private Tablero tablero;

    public VentanaJuego(ControladorJuego controlador, Tablero tablero) {
        this.controlador = controlador;
        this.tablero = tablero;
        inicializar();
        agregarObservadores();
    }

    private void inicializar() {
        frame = new JFrame("Locura Cromática");
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
		frame.setLayout(new BorderLayout());
        
		JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSuperior.setBorder(new EmptyBorder(5, 10, 5, 10));
		contadorIntentos = new JLabel("Intentos: 0");
		contadorIntentos.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		panelSuperior.add(contadorIntentos);

        panelTablero = new JPanel(new GridLayout(TAMAÑO, TAMAÑO));
        panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(panelTablero);

        botonesCasillas = new JButton[TAMAÑO][TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                botonesCasillas[i][j] = new JButton();
                botonesCasillas[i][j].setBackground(ColorCasilla.GRIS.obtenerColor());
                panelTablero.add(botonesCasillas[i][j]);
            }
        }
		frame.add(panelSuperior, BorderLayout.NORTH);
		frame.add(panelTablero, BorderLayout.CENTER);
    }
    
	public void actualizarContadorIntentos() {
		int intentos = tablero.getContIntentos();
		contadorIntentos.setText("Intentos: " + intentos);
	}

    private void agregarObservadores() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                final int fila = i, columna = j;
                botonesCasillas[i][j].addActionListener(e -> {
                    controlador.manejarClick(fila, columna);
                });
            }
        }
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
