package main;

import controlador.ControladorJuego;
import modelo.Tablero;
import vista.VentanaJuego;

public class Main {

	public static void main(String[] args) {
		int nivelInicial = 5;

		Tablero tablero = new Tablero(nivelInicial);
		ControladorJuego controlador = new ControladorJuego(tablero, nivelInicial);
		VentanaJuego vista = new VentanaJuego(controlador, nivelInicial);

		controlador.colocarVista(vista);
		vista.mostrar();
	}
}
