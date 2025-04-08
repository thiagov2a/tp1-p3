package main;

import controlador.ControladorJuego;
import modelo.Juego;
import vista.VentanaJuego;

public class Main {

	public static void main(String[] args) {
		Juego juego = new Juego();
		ControladorJuego controlador = new ControladorJuego(juego);
		int tamaño = juego.obtenerTablero().obtenerTamaño();

		VentanaJuego vista = new VentanaJuego(controlador, tamaño);
		controlador.colocarVista(vista);
		vista.mostrar();
	}
}
