package modelo;

public class Juego {

	private int nivelActual;
	private Tablero tablero;
	private int record;
	private boolean primerRecordGuardado;

	public Juego() {
		this.nivelActual = 5;
		this.tablero = new Tablero(nivelActual);
		this.record = 0;
		this.primerRecordGuardado = false;
	}

	public void avanzarNivel() {
		actualizarRecord(tablero.obtenerErrores());
		nivelActual++;
		this.tablero = new Tablero(nivelActual);
	}

	public void reiniciarNivel() {
		actualizarRecord(tablero.obtenerErrores());
		this.tablero = new Tablero(nivelActual);
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
		return this.nivelActual;
	}

	public Tablero obtenerTablero() {
		return this.tablero;
	}

	public int obtenerRecord() {
		return this.record;
	}
}
