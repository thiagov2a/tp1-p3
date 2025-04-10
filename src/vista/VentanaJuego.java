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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controlador.ControladorJuego;
import modelo.ColorCasilla;

public class VentanaJuego {

	private JFrame frame;
	private JPanel panelTablero;
	private JLabel labelErrores;
	private JLabel labelRecord;
	private JLabel labelTiempo;
	private JButton[][] botonesCasillas;
	private ControladorJuego controlador;
	private int tamaño;
	private int segundos;
	private Timer temporizador;

	public VentanaJuego(ControladorJuego controlador, int tamaño) {
		this.controlador = controlador;
		this.tamaño = tamaño;
		this.segundos = 0;
		inicializar();
		configurarListeners();
	}

	// TODO: Agregar label de nivelActual
	private void inicializar() {
		frame = new JFrame("Locura Cromática");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Panel superior con tres secciones: izquierda, centro y derecha
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Label izquierdo - Record
		labelRecord = new JLabel("Récord: 0");
		labelRecord.setFont(new Font("Segoe UI", Font.BOLD, 20));
		JPanel panelIzquierda = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelIzquierda.setOpaque(false);
		panelIzquierda.add(labelRecord);

		// Label central - Errores
		labelErrores = new JLabel("Errores: 0");
		labelErrores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelCentro.setOpaque(false);
		panelCentro.add(labelErrores);

		// Label derecho - Tiempo
		labelTiempo = new JLabel("Tiempo: 00:00");
		labelTiempo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelDerecha.setOpaque(false);
		panelDerecha.add(labelTiempo);

		// Añadir los tres al panel superior
		panelSuperior.add(panelIzquierda, BorderLayout.WEST);
		panelSuperior.add(panelCentro, BorderLayout.CENTER);
		panelSuperior.add(panelDerecha, BorderLayout.EAST);

		// Panel del tablero
		panelTablero = new JPanel(new GridLayout(tamaño, tamaño));
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));

		botonesCasillas = new JButton[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j] = new JButton();
				botonesCasillas[i][j].setBackground(ColorCasilla.GRIS.obtenerColor());
				panelTablero.add(botonesCasillas[i][j]);
			}
		}

		// Temporizador
		temporizador = new Timer(1000, e -> {
			segundos++;
			int minutos = segundos / 60;
			int segRestantes = segundos % 60;
			String tiempoFormateado = String.format("Tiempo: %02d:%02d", minutos, segRestantes);
			labelTiempo.setText(tiempoFormateado);
		});
		temporizador.start();

		JPanel panelPrincipal = (JPanel) frame.getContentPane();
		InputMap inputMap = panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panelPrincipal.getActionMap();
		KeyStroke salto = KeyStroke.getKeyStroke("shift S");
		inputMap.put(salto, "saltarNivel");
		actionMap.put("saltarNivel", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				mostrarPantallaVictoria();
			}
		});

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

	public void actualizarVista(Color[][] colores, int record, int errores) {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j].setBackground(colores[i][j]);
			}
		}
		this.labelRecord.setText("Record: " + record);
		this.labelErrores.setText("Errores: " + errores);
	}

	public void mostrarPantallaVictoria() {
		temporizador.stop();
		int opcion = JOptionPane.showOptionDialog(frame,
				"¡Nivel completado en " + String.format("%02d:%02d", segundos / 60, segundos % 60)
						+ "!\n¿Qué querés hacer?",
				"Nivel Completado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
				new Object[] { "Siguiente nivel", "Reintentar" }, "Siguiente nivel");

		frame.dispose();

		if (opcion == JOptionPane.YES_OPTION) {
			controlador.avanzarNivel();
		} else {
			controlador.reiniciarNivel();
		}
	}

	public void mostrar() {
		frame.setVisible(true);
	}
}
