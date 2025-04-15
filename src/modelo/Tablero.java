package modelo;

import java.awt.Color;

public class Tablero {

	private int tamaño;
	private Casilla[][] casillas;
	private int errores;

	public Tablero() {
		this(5); // Iniciar tablero en tamaño 5
	}

	public Tablero(int tamaño) {
		this.tamaño = tamaño;
		this.casillas = inicializarTablero(tamaño);
		this.errores = 0;
	}

	private Casilla[][] inicializarTablero(int tamaño) {
		Casilla[][] casillas = new Casilla[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				casillas[i][j] = new Casilla(i, j);
			}
		}
		return casillas;
	}
	
	public void cambiarColorCasilla(int fila, int columna) {
		this.casillas[fila][columna].cambiarColor();
		verificarYApagarCasillas(fila, columna);
	}

	private void verificarYApagarCasillas(int fila, int columna) {
		ColorCasilla colorCentral = casillas[fila][columna].obtenerColorCasilla();

		boolean izquierda = (columna > 0) && casillas[fila][columna - 1].obtenerColorCasilla() == colorCentral;
		boolean derecha = (columna < tamaño - 1) && casillas[fila][columna + 1].obtenerColorCasilla() == colorCentral;
		boolean arriba = (fila > 0) && casillas[fila - 1][columna].obtenerColorCasilla() == colorCentral;
		boolean abajo = (fila < tamaño - 1) && casillas[fila + 1][columna].obtenerColorCasilla() == colorCentral;

		if (izquierda || derecha || arriba || abajo) {
			if (fila > 0)
				casillas[fila - 1][columna].apagar();
			if (fila < tamaño - 1)
				casillas[fila + 1][columna].apagar();
			if (columna > 0)
				casillas[fila][columna - 1].apagar();
			if (columna < tamaño - 1)
				casillas[fila][columna + 1].apagar();
			casillas[fila][columna].apagar();
			
			errores++;
		}
	}

	public boolean verificarVictoria() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				if (casillas[i][j].obtenerColorCasilla().equals(ColorCasilla.GRIS)) {
					return false;
				}
			}
		}
		return true;
	}

	public int obtenerTamaño() {
		return this.tamaño;
	}

	public Color[][] obtenerColores() {
		Color[][] colores = new Color[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				colores[i][j] = casillas[i][j].obtenerColor();
			}
		}
		return colores;
	}

	public int obtenerErrores() {
		return errores;
	}
}
