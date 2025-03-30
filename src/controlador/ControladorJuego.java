package controlador;

import modelo.Tablero;
import vista.VentanaJuego;

public class ControladorJuego {
	
    private Tablero tablero;
    private VentanaJuego vista;

    public ControladorJuego(Tablero tablero, VentanaJuego vista) {
        this.tablero = tablero;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void manejarClick(int fila, int columna) {
        tablero.cambiarColorCasilla(fila, columna);
        vista.actualizarVista(tablero.getCasillas());

    }
}
