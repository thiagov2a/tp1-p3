package vista;

import java.awt.BorderLayout;
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
import modelo.Casilla;
import modelo.ColorCasilla;
import modelo.Tablero;

public class VentanaJuego {

	private JFrame frame;
	private JPanel panelTablero;
	private JLabel contadorIntentos;
	private JButton[][] botonesCasillas;
	private int tamaño;
	private ControladorJuego controlador;
	private Tablero tablero;

	public VentanaJuego(ControladorJuego controlador, Tablero tablero) {
		this.tamaño = tablero.getTamaño(); // Necesitás un getter en Tablero
		this.controlador = controlador;
		this.tablero = tablero;
		inicializar();
		verificarClick();
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

		panelTablero = new JPanel(new GridLayout(tamaño, tamaño));
		panelTablero.setBorder(new EmptyBorder(10, 10, 10, 10));
		frame.add(panelTablero);

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

	private void verificarClick() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				final int fila = i, columna = j;
				botonesCasillas[i][j].addActionListener(e -> {
					controlador.manejarClick(fila, columna);
				});
			}
		}
	}

	public void actualizarVista(Casilla[][] casillas) {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				botonesCasillas[i][j].setBackground(casillas[i][j].obtenerColor());
			}
		}
	}
	
	public void actualizarContadorIntentos() {
		int intentos = tablero.getContIntentos();
		contadorIntentos.setText("Intentos: " + intentos);
	}
	
	public void verificarVictoria() {
		if (tablero.verificarVictoria()) {
			int opcion = JOptionPane.showOptionDialog(
					frame,
					"¡Felicidades! Pasaste el tutorial. Ahora jugá de verdad. Elegí una dificultad:",
					"Nivel completado",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					new String[] { "10x10", "15x15", "20x20" },
					"10x10"
					);
			
			int nuevoTamaño = switch (opcion) {
			case 0 -> 10;
			case 1 -> 15;
			case 2 -> 20;
			default -> -1;
			};
			
			if (nuevoTamaño != -1) {
				frame.dispose(); // Cierra la ventana actual

				Tablero nuevoTablero = new Tablero(nuevoTamaño); // necesitás ajustar el constructor de Tablero
				ControladorJuego nuevoControlador = new ControladorJuego(nuevoTablero);
				VentanaJuego nuevaVista = new VentanaJuego(nuevoControlador, nuevoTablero);

				nuevoControlador.colocarVista(nuevaVista);
				nuevaVista.mostrar();
			}
		}
	}
	
	public void mostrar() {
		frame.setVisible(true);
	}
}
