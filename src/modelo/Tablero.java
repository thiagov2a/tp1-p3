package modelo;

public class Tablero {

	private Casilla[][] casillas;
	private int tamaño;
	private int cantIntentos;

	public Tablero() {
		this.casillas = new Casilla[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				this.casillas[i][j] = new Casilla(i, j);
			}
		}
		this.tamaño = 5;
		this.cantIntentos = 0;
	}

	public void cambiarColorCasilla(int fila, int columna) {
		casillas[fila][columna].cambiarColor();
		verificarYApagarCasillas(fila, columna);
		cantIntentos++;
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
		}
	}
	
	public boolean verificarVictoria() {
		return false;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public int getContIntentos() {
		return cantIntentos;
	}
}
