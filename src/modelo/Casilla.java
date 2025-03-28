package modelo;

public class Casilla {

	private int fila;
	private int columna;
	private boolean encendida;
	private Color color;

	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.encendida = false;
		this.color = Color.GRIS;
	}

	public void cambiarColor() {
		this.encendida = true;
		this.color = Color.obtenerColor();
	}

	public void apagar() {
		this.encendida = false;
		this.color = Color.GRIS;
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
		return this.color;
	}

}