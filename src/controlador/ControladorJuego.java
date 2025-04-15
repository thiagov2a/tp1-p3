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
		actualizarVista();
	}

	public void manejarClick(int fila, int columna) {
		Tablero tablero = juego.obtenerTablero();
		tablero.cambiarColorCasilla(fila, columna);
		actualizarVista();

		if (tablero.verificarVictoria()) {
			vista.mostrarPantallaVictoria();
		}
	}

	public void avanzarNivel() {
		juego.avanzarNivel();
		iniciarVistaDeNivel();
	}

	public void reiniciarNivel() {
		juego.reiniciarNivel();
		iniciarVistaDeNivel();
	}

	public void reiniciarDesdeNivelInicial() {
		juego.reiniciarDesdeNivel(5);
		iniciarVistaDeNivel();
	}

	private void iniciarVistaDeNivel() {
		int tamaño = juego.obtenerTablero().obtenerTamaño();
		VentanaJuego nuevaVista = new VentanaJuego(this, tamaño);
		colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}

	private void actualizarVista() {
		Tablero tablero = juego.obtenerTablero();
		vista.actualizarVista(tablero.obtenerColores(), juego.obtenerRecord(), tablero.obtenerErrores());
	}

	public boolean estaEnUltimoNivel() {
		return juego.obtenerNivelActual() > 6;
	}

	public void finalizarJuego() {
		vista.finalizarJuego();
	}

	public void cerrarAplicacion() {
		System.exit(0);
	}
}
