package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

	private void inicializar() {
		frame = new JFrame("Locura Cromática");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		labelTiempo = new JLabel("Tiempo: 00:00");
		labelTiempo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelTiempo.setBounds(250, 10, 220, 30);
		labelTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(labelTiempo);

		// Panel superior con contador de intentos
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
		panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

		labelRecord = new JLabel("Record: 0");
		labelRecord.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelRecord.setAlignmentX(JLabel.LEFT_ALIGNMENT);

		labelErrores = new JLabel("Errores: 0");
		labelErrores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		labelErrores.setAlignmentX(JLabel.LEFT_ALIGNMENT);

		panelSuperior.add(labelRecord);
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

		// TODO: Pasar método/lógica a Clase Juego.java
		// Temporizador que se ejecuta cada 1000 milisegundos (1 segundo)
		temporizador = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				segundos++;
				int minutos = segundos / 60;
				int segRestantes = segundos % 60;

				String tiempoFormateado = String.format("Tiempo: %02d:%02d", minutos, segRestantes);
				labelTiempo.setText(tiempoFormateado);
			}
		});
		temporizador.start();

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
		JOptionPane.showMessageDialog(frame,
				"¡Nivel completado en " + segundos / 60 + ":" + segundos % 60 + "! Vas al siguiente nivel.");
		frame.dispose();
		controlador.avanzarNivel();
	}

	public void mostrar() {
		frame.setVisible(true);
	}
}
