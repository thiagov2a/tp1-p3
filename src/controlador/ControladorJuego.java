package controlador;

import modelo.Tablero;
import vista.VentanaJuego;

public class ControladorJuego {

	private Tablero tablero;
	private VentanaJuego vista;
	private int nivelActual;

	public ControladorJuego(Tablero tablero, int nivelActual) {
		this.tablero = tablero;
		this.nivelActual = nivelActual;
	}

	public void colocarVista(VentanaJuego vista) {
		this.vista = vista;
	}

	public void manejarClick(int fila, int columna) {
		tablero.cambiarColorCasilla(fila, columna);

		vista.actualizarVista(tablero.obtenerColores(), tablero.getContIntentos());

		if (tablero.verificarVictoria()) {
			vista.mostrarPantallaVictoria();
		}
	}

	public void avanzarNivel() {
		nivelActual++;
		Tablero nuevoTablero = new Tablero(nivelActual);
		ControladorJuego nuevoControlador = new ControladorJuego(nuevoTablero, nivelActual);
		VentanaJuego nuevaVista = new VentanaJuego(nuevoControlador, nivelActual);

		nuevoControlador.colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}
}
