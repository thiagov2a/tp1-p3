package modelo;

public class Tablero {

	private Casilla[][] casillas;
	private final int TAMAÑO = 5;

	public Tablero() {
		this.casillas = new Casilla[TAMAÑO][TAMAÑO];

		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j < TAMAÑO; j++) {
				this.casillas[i][j] = new Casilla(i, j);
			}
		}
	}

}