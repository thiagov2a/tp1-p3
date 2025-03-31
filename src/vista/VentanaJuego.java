package vista;

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

	public VentanaJuego(ControladorJuego controlador) {
		this.controlador = controlador;
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
				final int fila = i, columna = j;

				botonesCasillas[i][j].addActionListener(e -> {
					controlador.manejarClick(fila, columna);
				});

				panelTablero.add(botonesCasillas[i][j]);
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
