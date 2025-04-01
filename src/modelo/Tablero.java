package modelo;

public class Tablero {

	private Casilla[][] casillas;
	private final int TAMAÑO = 5;
    private int contFallos=0;
    
	public Tablero() {
		this.casillas = new Casilla[TAMAÑO][TAMAÑO];
		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j < TAMAÑO; j++) {
				this.casillas[i][j] = new Casilla(i, j);
			}
			
		}
	}
	
	public void cambiarColorCasilla(int fila, int columna) {
		casillas[fila][columna].cambiarColor();
		verificarYApagarCasillas(fila, columna);
	}

	private void verificarYApagarCasillas(int fila, int columna) {
    ColorCasilla colorCentral = casillas[fila][columna].obtenerColorCasilla();

    boolean izquierda = (columna > 0) && casillas[fila][columna - 1].obtenerColorCasilla() == colorCentral;
    boolean derecha = (columna < TAMAÑO - 1) && casillas[fila][columna + 1].obtenerColorCasilla() == colorCentral;
    boolean arriba = (fila > 0) && casillas[fila - 1][columna].obtenerColorCasilla() == colorCentral;
    boolean abajo = (fila < TAMAÑO - 1) && casillas[fila + 1][columna].obtenerColorCasilla() == colorCentral;
     
    
    
    if (izquierda || derecha || arriba || abajo) {
        if (fila > 0) casillas[fila - 1][columna].apagar() ;
        if (fila < TAMAÑO - 1) casillas[fila + 1][columna].apagar();
        if (columna > 0) casillas[fila][columna - 1].apagar();
        if (columna < TAMAÑO - 1) casillas[fila][columna + 1].apagar();
        casillas[fila][columna].apagar(); // cuando se borra cualquiera de las celdas se borra automaticamente la del medio ç        
        contFallos=contFallos+ 1;
        System.out.println(contFallos);
    }
}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public int getContFallos() {
		return contFallos;
	}

	
}