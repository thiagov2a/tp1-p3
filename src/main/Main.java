package main;

import controlador.ControladorJuego;
import modelo.Tablero;
import vista.VentanaJuego;

public class Main {

	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		VentanaJuego vista = new VentanaJuego();
		ControladorJuego controlador = new ControladorJuego(tablero, vista);

		vista.setControlador(controlador);
		vista.mostrar();
	}
}
