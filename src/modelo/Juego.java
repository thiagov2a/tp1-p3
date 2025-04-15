package modelo;

public class Juego {

	private int nivelActual;
	private Tablero tablero;
	private int record;
	private boolean primerRecordGuardado;

	public Juego() {
		reiniciarDesdeNivel(5); // Iniciar juego en nivel 5
	}

	public void reiniciarNivel() {
		actualizarRecord(tablero.obtenerErrores());
		tablero = new Tablero(nivelActual);
	}

	public void reiniciarDesdeNivel(int nivel) {
		this.nivelActual = nivel;
		this.record = 0;
		this.primerRecordGuardado = false;
		this.tablero = new Tablero(nivelActual);
	}

	public void avanzarNivel() {
		record = 0;
		primerRecordGuardado = false;
		nivelActual++;
		tablero = new Tablero(nivelActual);
	}

	private void actualizarRecord(int errores) {
		if (!primerRecordGuardado) {
			this.record = errores;
			primerRecordGuardado = true;
		} else if (errores < this.record) {
			this.record = errores;
		}
	}

	public int obtenerNivelActual() {
		return nivelActual;
	}

	public Tablero obtenerTablero() {
		return tablero;
	}

	public int obtenerRecord() {
		return record;
	}
}
