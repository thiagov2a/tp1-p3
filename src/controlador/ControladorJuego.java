package controlador;

import modelo.Juego;
import modelo.Tablero;
import vista.VentanaJuego;

public class ControladorJuego {

	private Juego juego;
	private VentanaJuego vista;

	public ControladorJuego(Juego juego) {
		this.juego = juego;
	}

	public void colocarVista(VentanaJuego vista) {
		this.vista = vista;
	}

	public void manejarClick(int fila, int columna) {
		Tablero tablero = this.juego.obtenerTablero();
		
		tablero.cambiarColorCasilla(fila, columna);
		vista.actualizarVista(tablero.obtenerColores(), tablero.obtenerErrores());

		if (tablero.verificarVictoria()) {
			vista.mostrarPantallaVictoria();
		}
	}

	public void avanzarNivel() {
		this.juego.avanzarNivel();
		int tamaño = juego.obtenerTablero().obtenerTamaño();

		VentanaJuego nuevaVista = new VentanaJuego(this, tamaño);
		colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}
}
