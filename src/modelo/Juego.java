package modelo;

public class Juego {

	private int nivelActual;
	private Tablero tablero;

	public Juego() {
		this.nivelActual = 5;
		this.tablero = new Tablero(nivelActual);
	}

	public void avanzarNivel() {
		nivelActual++;
		this.tablero = new Tablero(nivelActual);
	}

	public int obtenerNivelActual() {
		return this.nivelActual;
	}

	public Tablero obtenerTablero() {
		return this.tablero;
	}
}
