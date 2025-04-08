package modelo;

import java.awt.Color;

public class Casilla {

	private ColorCasilla colorCasilla;

	public Casilla() {
	}

	public Casilla(int fila, int columna) {
		this.colorCasilla = ColorCasilla.GRIS;
	}

	public void cambiarColor() {
		this.colorCasilla = ColorCasilla.cambiarColor();
	}

	public void apagar() {
		this.colorCasilla = ColorCasilla.GRIS;
	}

	public ColorCasilla obtenerColorCasilla() {
		return this.colorCasilla;
	}

	public Color obtenerColor() {
		return this.colorCasilla.obtenerColor();
	}
}
