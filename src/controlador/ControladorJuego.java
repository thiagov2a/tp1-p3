package controlador;

import modelo.Tablero;
import vista.VentanaJuego;

public class ControladorJuego {

	private Tablero tablero;
	private VentanaJuego vista;


	public ControladorJuego(Tablero tablero) {
		this.tablero = tablero;
	}

	public void manejarClick(int fila, int columna) {
		tablero.cambiarColorCasilla(fila, columna);
		vista.actualizarVista(tablero.getCasillas());
	}

	public void colocarVista(VentanaJuego vista) {
		this.vista = vista;
	}
}
