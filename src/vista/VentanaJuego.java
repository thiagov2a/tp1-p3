package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import controlador.ControladorJuego;
import modelo.ColorCasilla;

public class VentanaJuego {

	private JFrame frame;
	private JPanel panelSuperior;
	private JPanel panelTablero;
	private JLabel labelNivel;
	private JLabel labelErrores;
	private JLabel labelRecord;
	private JButton[][] botonesCasillas;
	private ControladorJuego controlador;
	private int tamaño;

	public VentanaJuego(ControladorJuego controlador, int tamaño) {
		this.controlador = controlador;
		this.tamaño = tamaño;
		inicializarVista();
		inicializarListeners();
	}

	private void inicializarVista() {
		frame = new JFrame("Locura Cromática");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		panelSuperior = crearPanelSuperior();
		panelTablero = crearPanelTablero();

		frame.add(panelSuperior, BorderLayout.NORTH);
		frame.add(panelTablero, BorderLayout.CENTER);

		configurarAtajoDeTeclado();
	}

	private JPanel crearPanelSuperior() {
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

		labelNivel = crearEtiqueta("Nivel: 0");
		labelRecord = crearEtiqueta("Récord: 0");
		labelErrores = crearEtiqueta("Errores: 0");

		JPanel panelIzquierda = crearSubpanel(labelNivel, FlowLayout.LEFT);
		JPanel panelCentro = crearSubpanel(labelRecord, FlowLayout.CENTER);
		JPanel panelDerecha = crearSubpanel(labelErrores, FlowLayout.RIGHT);

		panelSuperior.add(panelIzquierda, BorderLayout.WEST);
		panelSuperior.add(panelCentro, BorderLayout.CENTER);
		panelSuperior.add(panelDerecha, BorderLayout.EAST);

		return panelSuperior;
	}

	private JLabel crearEtiqueta(String texto) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 20));
		return etiqueta;
	}

	private JPanel crearSubpanel(JLabel etiqueta, int alineacion) {
		JPanel panel = new JPanel(new FlowLayout(alineacion));
		panel.setOpaque(false);
		panel.add(etiqueta);
		return panel;
	}

	private JPanel crearPanelTablero() {
		JPanel panel = new JPanel(new GridLayout(tamaño, tamaño));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		botonesCasillas = new JButton[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j] = new JButton();
				botonesCasillas[i][j].setBackground(ColorCasilla.GRIS.obtenerColor());
				panel.add(botonesCasillas[i][j]);
			}
		}
		return panel;
	}

	private void configurarAtajoDeTeclado() {
		JPanel panelPrincipal = (JPanel) frame.getContentPane();
		InputMap inputMap = panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panelPrincipal.getActionMap();
		KeyStroke salto = KeyStroke.getKeyStroke("shift S");

		inputMap.put(salto, "saltarNivel");
		actionMap.put("saltarNivel", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				mostrarPantallaVictoria();
			}
		});
	}

	private void inicializarListeners() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				final int fila = i, columna = j;
				botonesCasillas[i][j].addActionListener(e -> controlador.manejarClick(fila, columna));
			}
		}
	}

	public void actualizarVista(Color[][] colores, int record, int errores) {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j].setBackground(colores[i][j]);
			}
		}
		labelNivel.setText("Nivel: " + tamaño);
		labelRecord.setText("Récord: " + record);
		labelErrores.setText("Errores: " + errores);
	}

	public void mostrarPantallaVictoria() {
		if (controlador.estaEnUltimoNivel()) {
			controlador.finalizarJuego();
			return;
		}

		Object[] opciones = { "Siguiente nivel", "Reintentar" };
		int opcion = JOptionPane.showOptionDialog(
				frame,
				"¡Nivel completado!\n¿Qué querés hacer?",
				"Nivel Completado",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				opciones,
				opciones[0]);

		frame.dispose();

		if (opcion == JOptionPane.YES_OPTION) {
			controlador.avanzarNivel();
		} else {
			controlador.reiniciarNivel();
		}
	}

	public void finalizarJuego() {
		Object[] opciones = { "Sí", "No" };
		int opcion = JOptionPane.showOptionDialog(
				frame,
				"¡Has completado todos los niveles!\n¿Querés jugar de nuevo?",
				"Juego Completado",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				opciones,
				opciones[0]);

		frame.dispose();

		if (opcion == JOptionPane.YES_OPTION) {
			controlador.reiniciarDesdeNivelInicial();
		} else {
			controlador.cerrarAplicacion();
		}
	}

	public void mostrar() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
