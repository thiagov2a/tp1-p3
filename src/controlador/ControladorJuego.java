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
		actualizarVista(); // Actualiza vista al asignarla
	}

	public void manejarClick(int fila, int columna) {
		juego.obtenerTablero().cambiarColorCasilla(fila, columna);
		actualizarVista();

		if (juego.obtenerTablero().verificarVictoria()) {
			vista.mostrarPantallaVictoria();
		}
	}

	public void avanzarNivel() {
		juego.avanzarNivel();
		int tamaño = juego.obtenerTablero().obtenerTamaño();

		VentanaJuego nuevaVista = new VentanaJuego(this, tamaño);
		colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}

	private void actualizarVista() {
		Tablero tablero = juego.obtenerTablero();
		vista.actualizarVista(tablero.obtenerColores(), juego.obtenerRecord(), tablero.obtenerErrores());
	}

	public void reiniciarNivel() {
		// TODO Auto-generated method stub
	}
}
