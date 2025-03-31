package main;

import controlador.ControladorJuego;
import modelo.Tablero;
import vista.VentanaJuego;

public class Main {
	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		ControladorJuego controlador = new ControladorJuego(tablero);
		VentanaJuego vista = new VentanaJuego(controlador);

		controlador.colocarVista(vista);
		vista.mostrar();
	}
}
