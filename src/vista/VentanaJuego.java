package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorJuego;
import modelo.ColorCasilla;

public class VentanaJuego {

	private JFrame frame;
	private JPanel panelTablero;
	private JLabel labelErrores;
	private JButton[][] botonesCasillas;
	private ControladorJuego controlador;
	private int tamaño;

	public VentanaJuego(ControladorJuego controlador, int tamaño) {
		this.controlador = controlador;
		this.tamaño = tamaño;
		inicializar();
		configurarListeners();
	}

	private void inicializar() {
		frame = new JFrame("Locura Cromática");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Panel superior con contador de intentos
		JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSuperior.setBorder(new EmptyBorder(5, 10, 5, 10));
		labelErrores = new JLabel("Errores: 0");
		labelErrores.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelSuperior.add(labelErrores);

		// Panel con el tablero
		panelTablero = new JPanel(new GridLayout(tamaño, tamaño));
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Inicializar botones
		botonesCasillas = new JButton[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j] = new JButton();
				botonesCasillas[i][j].setBackground(ColorCasilla.GRIS.obtenerColor());
				panelTablero.add(botonesCasillas[i][j]);
			}
		}

		frame.add(panelSuperior, BorderLayout.NORTH);
		frame.add(panelTablero, BorderLayout.CENTER);
	}

	private void configurarListeners() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				final int fila = i, columna = j;
				botonesCasillas[i][j].addActionListener(e -> controlador.manejarClick(fila, columna));
			}
		}
	}

	public void actualizarVista(Color[][] colores, int errores) {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j].setBackground(colores[i][j]);
			}
		}
		this.labelErrores.setText("Errores: " + errores);
	}

	public void mostrarPantallaVictoria() {
		JOptionPane.showMessageDialog(frame, "¡Nivel completado! Vas al siguiente nivel.");
		frame.dispose();
		controlador.avanzarNivel();
	}

	public void mostrar() {
		frame.setVisible(true);
	}
}
