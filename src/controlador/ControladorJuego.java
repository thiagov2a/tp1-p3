package controlador;

import modelo.Tablero;
import vista.VentanaJuego;

public class ControladorJuego {

	private Tablero tablero;
	private VentanaJuego vista;

	public ControladorJuego(Tablero tablero) {
		this.tablero = tablero;
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

	public void reiniciarJuego(int nuevoTamaño) {
		Tablero nuevoTablero = new Tablero(nuevoTamaño);
		ControladorJuego nuevoControlador = new ControladorJuego(nuevoTablero);
		VentanaJuego nuevaVista = new VentanaJuego(nuevoControlador, nuevoTamaño);

		nuevoControlador.colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}
}
