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

	public void colocarVista(VentanaJuego nuevaVista) {
		this.vista = nuevaVista;
		actualizarVista();
	}

	public void manejarClick(int fila, int columna) {
		juego.obtenerTablero().cambiarColorCasilla(fila, columna);
		actualizarVista();

		if (juego.obtenerTablero().verificarVictoria()) {
			vista.mostrarPantallaVictoria();
		}
	}

	public void avanzarNivel() {
	int nivel = juego.obtenerNivelActual();
	if(nivel <=6) {
	juego.avanzarNivel();
	iniciarNuevaVista();
	}
	else {
	vista.finDelJuego();
	}
}

	public void reiniciarNivel() {
		juego.reiniciarNivel();
		iniciarNuevaVista();
	}

	private void iniciarNuevaVista() {
		int tamaño = juego.obtenerTablero().obtenerTamaño();
		VentanaJuego nuevaVista = new VentanaJuego(this, tamaño);
		colocarVista(nuevaVista);
		nuevaVista.mostrar();
	}

	private void actualizarVista() {
		Tablero tablero = juego.obtenerTablero();
		vista.actualizarVista(tablero.obtenerColores(), juego.obtenerRecord(), tablero.obtenerErrores());
	}
	public void reiniciarJuego() {
        juego.reiniciarLV();
        iniciarNuevaVista();
	}
}
