package modelo;

import java.awt.Color;

public class Casilla {

	private int fila;
	private int columna;
	private boolean encendida;
	private ColorCasilla colorCasilla;

	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.encendida = false;
		this.colorCasilla = ColorCasilla.GRIS;
	}

	public void cambiarColor() {
		this.encendida = true;
		this.colorCasilla = ColorCasilla.cambiarColor();
	}

	public void apagar() {
		this.encendida = false;
		this.colorCasilla = ColorCasilla.GRIS;
	}

	public int obtenerFila() {
		return this.fila;
	}

	public int obtenerColumna() {
		return this.columna;
	}

	public boolean estaEncendida() {
		return this.encendida;
	}

	public Color obtenerColor() {
		return this.colorCasilla.obtenerColor();
	}

	public ColorCasilla obtenerColorCasilla() {
		return this.colorCasilla;
	}
}